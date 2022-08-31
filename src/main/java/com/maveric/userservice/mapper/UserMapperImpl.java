package com.maveric.userservice.mapper;


import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User map(UserResponse userResponse) {
        return new User(
                userResponse.get_id(),
                userResponse.getFirstName(),
                userResponse.getLastName(),
                userResponse.getMiddleName(),
                userResponse.getPhoneNumber(),
                userResponse.getEmail(),
                userResponse.getAddress(),
                userResponse.getDateOfBirth(),
                userResponse.getGender(),
                userResponse.getRole(),
                userResponse.getPassword()

        );
    }

    @Override
    public UserResponse map(User user) {
        return new UserResponse(
                user.get_id(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getAddress(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getRole(),
                user.getPassword()
        );
    }

    @Override
    public List<User> map(List<UserResponse> userResponses) {
        List<User> list = new ArrayList<User>(userResponses.size());
        for(UserResponse userDto:userResponses)
        {
            list.add(map(userDto));
        }
        return list;
    }

    @Override
    public List<UserResponse> mapToDto(List<User> users) {
        return users.stream().map(userResponse -> new UserResponse(
                userResponse.get_id(),
                userResponse.getFirstName(),
                userResponse.getLastName(),
                userResponse.getMiddleName(),
                userResponse.getPhoneNumber(),
                userResponse.getEmail(),
                userResponse.getAddress(),
                userResponse.getDateOfBirth(),
                userResponse.getGender(),
                userResponse.getRole(),
                userResponse.getPassword()
        )).toList();
    }
}
