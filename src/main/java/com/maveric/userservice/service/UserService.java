package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.model.User;

import java.util.List;

public interface UserService {
    public List<UserResponse> getUsers(Integer page,Integer pageSize);
    public UserResponse createUser(UserResponse userResponse);

    public UserResponse getUserDetails(String userId);

    public String deleteUser(String userId);

    public UserResponse getUserDetailsByEmail(String emailId);

    public UserResponse updateUser(String userId,UserResponse userResponse);
}
