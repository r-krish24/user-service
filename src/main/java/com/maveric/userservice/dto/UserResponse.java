package com.maveric.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.enumeration.Gender;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserResponse {

    private String _id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String address;
    private String dateOfBirth;
    private Gender gender;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String Password;
}
