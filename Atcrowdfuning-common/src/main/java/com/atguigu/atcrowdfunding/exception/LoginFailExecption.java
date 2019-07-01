package com.atguigu.atcrowdfunding.exception;

public class LoginFailExecption extends RuntimeException {


    static final long serialVersionUID = 1L;
    public  LoginFailExecption(String message){
        super(message);
    }

}
