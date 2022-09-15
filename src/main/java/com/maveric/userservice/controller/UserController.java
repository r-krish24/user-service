package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    UserService userService;
    /* Returns list of All Users */
    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        List<UserDto> userRespons = userService.getUsers(page,pageSize);

        return new ResponseEntity<>(userRespons, HttpStatus.OK);
    }

    /* Saving new user */
    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDtoResponse = userService.createUser(userDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }

    /* Returns list of user details by userId */
    @GetMapping("users/{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId) {
        UserDto userDtoResponse = userService.getUserDetails(userId);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);

    }
    /* Deleting user by userId */
    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        String result = userService.deleteUser(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /* Update User by UserId */
    @PutMapping("users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        if(userId.equals(userDto.get_id())) {
            UserDto userDto1 = userService.updateUser(userId, userDto);
            return new ResponseEntity<>(userDto1, HttpStatus.OK);
        }
        else
        {
            throw new UserNotFoundException("Requested User not found id " + userDto.get_id());
        }
    }
    /* Returns list details by emailId */
    @GetMapping("users/getUserByEmail/{emailId}")
    public ResponseEntity<UserDto> getUserDetailsByEmail(@PathVariable String emailId) {
        UserDto userDtoResponse = userService.getUserDetailsByEmail(emailId);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);

    }
}
