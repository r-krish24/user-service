package com.maveric.userservice.mapper;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.enumeration.Gender;
import com.maveric.userservice.model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static com.maveric.userservice.UserServiceApplicationTests.getUserDto;
import static org.junit.Assert.assertEquals;
public class UserMapperTest {
    @Autowired
    private UserMapperImpl mapper;

    @BeforeEach
    void setup(){
        UserMapperImpl mapper = new UserMapperImpl();
    }


    @Test
    public void testUserMapper(){
        UserDto userDto=getUserDto();
        User user = mapper.map(userDto);
        assertEquals(getUserDto().getGender(), user.getGender());
    }
}
