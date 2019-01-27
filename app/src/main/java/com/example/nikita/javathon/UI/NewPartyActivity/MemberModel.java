package com.example.nikita.javathon.UI.NewPartyActivity;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

public class MemberModel {

    @Expose
    @Getter
    @Setter
    private long id;

    @Expose
    @Getter
    @Setter
    private String nickname;

    @Expose
    @Getter
    @Setter
    private double balance;

    @Expose
    @Getter
    @Setter
    private String cardNumber;

}
