package com.example.nikita.javathon.UI.Auth;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class SignInRequest {
    @SerializedName("login") @Expose @Getter @Setter private String login;
    @SerializedName("password") @Expose @Getter @Setter private String password;

    public SignInRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

}