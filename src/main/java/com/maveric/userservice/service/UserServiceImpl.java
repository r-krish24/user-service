package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.mapper.UserMapper;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;
    public List<UserResponse> getUsers(Integer page, Integer pageSize) {
        Pageable paging = (Pageable) PageRequest.of(page, pageSize);
        Page<User> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            return pageResult.getContent().stream()
                    .map(
                            user -> mapper.map(user)
                    ).collect(
                            Collectors.toList()
                    );
        } else {
            return new ArrayList<>();
        }
    }
    public UserResponse createUser(UserResponse userResponse) {
        //Adding CreatedTime

        User user = mapper.map(userResponse);
        User userResult = repository.save(user);
        return  mapper.map(userResult);
    }
    @Override
    public UserResponse getUserDetails(String userId) {
        User transactionResult=repository.findById(Long.valueOf(userId)).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.map(transactionResult);
    }
    @Override
    public String deleteUser(String userId) {
        repository.deleteById(Long.valueOf(userId));
        return "Transaction deleted successfully.";
    }

    @Override
    public UserResponse getUserDetailsByEmail(String emailId) {
        User transactionResult=repository.findByEmail(emailId);//.orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.map(transactionResult);
    }

    @Override
    public UserResponse updateUser(String userId, UserResponse userResponse) {
        User userResult=repository.findById(Long.valueOf(userId)).orElseThrow(() -> new UserNotFoundException("User not found"));
        userResult.setId(userResult.getId());
        userResult.setFirstName(userResponse.getFirstName());
        userResult.setLastName(userResponse.getLastName());
        userResult.setMiddleName(userResult.getMiddleName());
        userResult.setPhoneNumber(userResult.getPhoneNumber());
        userResult.setEmail(userResult.getEmail());
        userResult.setAddress(userResult.getAddress());
        userResult.setDateOfBirth(userResult.getDateOfBirth());
        userResult.setGender(userResult.getGender());
        User accountUpdated = repository.save(userResult);
        return mapper.map(accountUpdated);
    }
}
