package com.example.nikita.javathon.UI.ProductsList;

import android.support.annotation.NonNull;

import com.example.nikita.javathon.Status;

import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.nikita.javathon.Status.*;

public class ListViewState {

    public final Status status;

    @Nullable
    public List<ProductsModel> data;

    @Nullable
    public final String error;

    private ListViewState(Status status, @Nullable List<ProductsModel> data, @Nullable String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ListViewState loading() {
        return new ListViewState(LOADING, null, null);
    }

    public static ListViewState success(@NonNull List<ProductsModel> data) {
        return new ListViewState(SUCCESS, data, null);
    }

    public static ListViewState error(@NonNull String error) {
        return new ListViewState(ERROR, null, error);
    }

}