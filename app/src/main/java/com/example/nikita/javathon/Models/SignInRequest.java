package com.example.nikita.javathon.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInRequest {

    @Expose
    @SerializedName("login")
    private String login;


    @SerializedName("password")
    @Expose
    private String password;

    public SignInRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public SignInRequest(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
