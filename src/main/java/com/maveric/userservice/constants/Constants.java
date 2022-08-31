package com.maveric.userservice.constants;

import java.time.LocalDateTime;


public class Constants {

    private Constants()
    {

    }

    public static final String USER_NOT_FOUND_CODE="404";
    public static final String USER_NOT_FOUND_MESSAGE="User not Found for Id-";
    public static final String USER_DELETED_SUCCESS="User deleted successfully.";
    public static final String METHOD_NOT_ALLOWED_CODE="405";
    public static final String METHOD_NOT_ALLOWED_MESSAGE="Method Not Allowed. Kindly check the Request URL and Request Type.";
    public static final String BAD_REQUEST_CODE="400";
    public static final String BAD_REQUEST_MESSAGE="Invalid inputs!";
    public static LocalDateTime getCurrentDateTime() {
        return (LocalDateTime.now());
    }
}
