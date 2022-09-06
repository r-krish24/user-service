package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.mapper.UserMapper;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserDto> getUsers(Integer page, Integer pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        Page<User> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            List<User> users = pageResult.getContent();
            return mapper.mapToDto(users);
        } else {
            return new ArrayList<>();
        }
    }
    public UserDto createUser(UserDto userDto) {
        String pass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(pass);
        System.out.println(pass);
        User user = mapper.map(userDto);
        User userResult = repository.save(user);
        return  mapper.map(userResult);
    }
    @Override
    public UserDto getUserDetails(String userId) {
        User userResult=repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        return mapper.map(userResult);
    }
    @Override
    public String deleteUser(String userId) {
        repository.deleteById(userId);
        return "User deleted successfully.";
    }

    @Override
    public UserDto getUserDetailsByEmail(String emailId) {
        User userResult=repository.findByEmail(emailId);
        return mapper.map(userResult);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User userResult=repository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userResult.set_id(userResult.get_id());
        userResult.setFirstName(userDto.getFirstName());
        userResult.setLastName(userDto.getLastName());
        userResult.setMiddleName(userDto.getMiddleName());
        userResult.setPhoneNumber(userDto.getPhoneNumber());
        userResult.setEmail(userDto.getEmail());
        userResult.setAddress(userDto.getAddress());
        userResult.setDateOfBirth(userDto.getDateOfBirth());
        userResult.setGender(userDto.getGender());
        User accountUpdated = repository.save(userResult);
        return mapper.map(accountUpdated);
    }
}
