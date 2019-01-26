package com.example.nikita.javathon.UI.ProductDetails;

import android.support.annotation.NonNull;

import com.example.nikita.javathon.Status;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.nikita.javathon.Status.ERROR;
import static com.example.nikita.javathon.Status.LOADING;
import static com.example.nikita.javathon.Status.SUCCESS;

public class ProductDetailsViewState {

    public final Status status;

    @Nullable
    public final List<ProductDetailsListModel> data;

    @Nullable
    public final String error;

    private ProductDetailsViewState(Status status, @Nullable List<ProductDetailsListModel> data, @Nullable String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ProductDetailsViewState loading() {
        return new ProductDetailsViewState(LOADING, null, null);
    }

    public static ProductDetailsViewState success(@NonNull List<ProductDetailsListModel> data) {
        return new ProductDetailsViewState(SUCCESS, data, null);
    }

    public static ProductDetailsViewState error(@NonNull String error) {
        return new ProductDetailsViewState(ERROR, null, error);
    }

}