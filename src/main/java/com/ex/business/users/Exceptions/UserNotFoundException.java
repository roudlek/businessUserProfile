package com.ex.business.users.Exceptions;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 999;
    public UserNotFoundException(String message){super(message);}

    public static String printUserNotFound(){
        return "User not found";
    }
}
