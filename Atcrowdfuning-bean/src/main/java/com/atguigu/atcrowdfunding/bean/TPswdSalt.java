package com.atguigu.atcrowdfunding.bean;

public class TPswdSalt {


    //验证密码与盐值的类
    private  String password;

    private String  salt;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "TPswdSalt{" +
                "password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
