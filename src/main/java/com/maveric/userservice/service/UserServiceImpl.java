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
        Pageable paging = PageRequest.of(page, pageSize);
        Page<User> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            List<User> users = pageResult.getContent();
            return mapper.mapToDto(users);
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
        User userResult=repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.map(userResult);
    }
    @Override
    public String deleteUser(String userId) {
        repository.deleteById(userId);
        return "User deleted successfully.";
    }

    @Override
    public UserResponse getUserDetailsByEmail(String emailId) {
        User userResult=repository.findByEmail(emailId);//.orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.map(userResult);
    }

    @Override
    public UserResponse updateUser(String userId, UserResponse userResponse) {
        User userResult=repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userResult.set_id(userResult.get_id());
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
