package com.example.nikita.javathon.Models;

import com.google.gson.annotations.SerializedName;

public class UserDTO {
    @SerializedName("id")
    private long id;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("balance")
    private double balance;

    @SerializedName("cardNumber")
    private String cardNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
