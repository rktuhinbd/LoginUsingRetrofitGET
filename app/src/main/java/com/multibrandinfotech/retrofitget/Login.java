package com.multibrandinfotech.retrofitget;

/**
 * Created by Md. Rejaul Karim on 1/6/2019.
 * Copyright (c) 2019 MULTIBRAND INFOTECH LTD
 */
public class Login {
    private String UserName;
    private String Password;

    public Login(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }
}
