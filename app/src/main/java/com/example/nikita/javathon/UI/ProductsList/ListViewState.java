package com.example.nikita.javathon.UI.ProductsList;

import android.support.annotation.NonNull;

import com.example.nikita.javathon.Enums.AddStatus;

import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.nikita.javathon.Enums.AddStatus.*;

public class ListViewState {

    public final AddStatus status;

    @Nullable
    public List<ProductsModel> data;

    @Nullable
    public ProductsModel singleData;


    @Nullable
    public final String message;

    private ListViewState(AddStatus status,
                          @Nullable List<ProductsModel> data,
                          @Nullable String error,
                          @Nullable ProductsModel singleData) {
        this.status = status;
        this.data = data;
        this.message = error;
        this.singleData = singleData;
    }

    public static ListViewState loading() {
        return new ListViewState(LOADING, null, null, null);
    }

    public static ListViewState success(@NonNull List<ProductsModel> data) {
        return new ListViewState(SUCCESS, data, null, null);
    }

    public static ListViewState error(@NonNull String error) {
        return new ListViewState(ERROR, null, error, null);
    }

    public static ListViewState added(@NonNull ProductsModel data) {
        return new ListViewState(ADDED, null, null, data);
    }

    public static ListViewState sent(@NonNull String message) {
        return new ListViewState(SENT, null, message, null);
    }

}