package com.example.nikita.javathon.UI.PartyList;

import android.support.annotation.NonNull;

import com.example.nikita.javathon.Enums.AddStatus;

import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.nikita.javathon.Enums.AddStatus.*;

public class PartyListViewState {

    public final AddStatus status;

    @Nullable
    public final List<PartyListModel> data;

    @Nullable
    public final PartyListModel singleData;

    @Nullable
    public final String error;

    private PartyListViewState(AddStatus status,
                               @Nullable List<PartyListModel> data,
                               @Nullable String error,
                               @Nullable PartyListModel singleData) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.singleData = singleData;
    }

    public static PartyListViewState loading() {
        return new PartyListViewState(LOADING, null, null, null);
    }

    public static PartyListViewState success(@NonNull List<PartyListModel> data) {
        return new PartyListViewState(SUCCESS, data, null, null);
    }

    public static PartyListViewState error(@NonNull String error) {
        return new PartyListViewState(ERROR, null, error, null);
    }

    public static PartyListViewState added(@NonNull PartyListModel data) {
        return new PartyListViewState(ADDED, null, null, data);
    }
}
