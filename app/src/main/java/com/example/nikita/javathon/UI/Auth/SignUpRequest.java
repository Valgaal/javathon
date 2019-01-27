package com.example.nikita.javathon.UI.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SignUpRequest implements Serializable {
    @SerializedName("login") @Expose
    @Getter @Setter private String login;
    @SerializedName("password") @Expose @Getter @Setter private String password;
    @SerializedName("cardNumber") @Expose @Getter @Setter private String cardNumber;
}
