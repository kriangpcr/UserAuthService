package com.example.demo.exception;

public class UserException extends BaseException{

    public UserException(String code) {
        super("user " + code);
    }
    public static UserException requestNull(){
        return new UserException("request is null");
    }
    public static UserException nameNull(){
        return new UserException("name is null");
    }
    // register

    public static UserException createEmailNull(){
        return new UserException("email is null");
    }
    public static UserException createEmailDupe(){
        return new UserException("email is Dupe");
    }
    public static UserException createNameDupe(){
        return new UserException("name is Dupe");
    }
    public static UserException createNameNull(){
        return new UserException("name is null");
    }
    public static UserException createPasswordNull(){
        return new UserException("password is null");
    }


    // login

    public static UserException loginEmailNotFound(){
        return new UserException("email not found");
    }
    public static UserException loginPasswordIncorrect(){
        return new UserException("password incorrect");
    }

    //user
    public static UserException userNotFound(){
        return new UserException("not found ");
    }

}
