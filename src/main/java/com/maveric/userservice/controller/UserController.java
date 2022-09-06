package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
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

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        List<UserDto> userRespons = userService.getUsers(page,pageSize);
        return new ResponseEntity<List<UserDto>>(userRespons, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDtoResponse = userService.createUser(userDto);
        return new ResponseEntity<UserDto>(userDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId) {
        UserDto userDtoResponse = userService.getUserDetails(userId);
        return new ResponseEntity<UserDto>(userDtoResponse, HttpStatus.OK);

    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        String result = userService.deleteUser(userId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PutMapping("users/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.updateUser(userId, userDto);
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);
    }

    @GetMapping("users/getUserByEmail/{emailId}")
    public ResponseEntity<UserDto> getUserDetailsByEmail(@PathVariable String emailId) {
        UserDto userDtoResponse = userService.getUserDetailsByEmail(emailId);
        return new ResponseEntity<UserDto>(userDtoResponse, HttpStatus.OK);

    }
}
