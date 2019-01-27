package com.example.nikita.javathon.UI.PartyList;

import com.example.nikita.javathon.UI.NewPartyActivity.MemberModel;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PartyListModel {

    @SerializedName("id")
    @Expose
    @Getter
    @Setter
    private long id;

    @SerializedName("name")
    @Expose
    @Getter
    @Setter
    private String name;

    @SerializedName("description")
    @Expose
    @Getter
    @Setter
    private String description;

    @SerializedName("members")
    @Expose
    @Getter
    @Setter
    private List<MemberModel> members;

    @SerializedName("items")
    @Expose
    @Getter
    @Setter
    private List<ProductsModel> items;
}
