package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        List<UserResponse> userResponses = userService.getUsers(page,pageSize);
        return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserResponse userResponse) {
        UserResponse userDtoResponse = userService.createUser(userResponse);
        return new ResponseEntity<UserResponse>(userDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable String userId) {
        UserResponse userDtoResponse = userService.getUserDetails(userId);
        return new ResponseEntity<UserResponse>(userDtoResponse, HttpStatus.OK);

    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        String result = userService.deleteUser(userId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PutMapping("users/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId,@RequestBody UserResponse userResponse) {
        UserResponse balanceDtoResponse = userService.updateUser(userId,userResponse);
        return new ResponseEntity<UserResponse>(balanceDtoResponse, HttpStatus.OK);
    }

    @GetMapping("users/getUserByEmail/{emailId}")
    public ResponseEntity<UserResponse> getUserDetailsByEmail(@PathVariable String emailId) {
        UserResponse userDtoResponse = userService.getUserDetailsByEmail(emailId);
        return new ResponseEntity<UserResponse>(userDtoResponse, HttpStatus.OK);

    }
}
