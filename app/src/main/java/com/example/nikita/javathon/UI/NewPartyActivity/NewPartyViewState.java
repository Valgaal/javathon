package com.example.nikita.javathon.UI.NewPartyActivity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nikita.javathon.Enums.NewPartyStatus;

import java.util.List;

import static com.example.nikita.javathon.Enums.NewPartyStatus.ADDED;
import static com.example.nikita.javathon.Enums.NewPartyStatus.ERROR;
import static com.example.nikita.javathon.Enums.NewPartyStatus.LOADING;
import static com.example.nikita.javathon.Enums.NewPartyStatus.REMOVED;
import static com.example.nikita.javathon.Enums.NewPartyStatus.SUCCESS;


public class NewPartyViewState {

    public final NewPartyStatus status;

    @Nullable
    public final List<MemberModel> data;

    @Nullable
    public final MemberModel singleData;

    @Nullable
    public final String error;

    private NewPartyViewState(NewPartyStatus status,
                               @Nullable List<MemberModel> data,
                               @Nullable String error,
                               @Nullable MemberModel singleData) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.singleData = singleData;
    }

    public static NewPartyViewState loading() {
        return new NewPartyViewState(LOADING, null, null, null);
    }

    public static NewPartyViewState success(@NonNull List<MemberModel> data) {
        return new NewPartyViewState(SUCCESS, data, null, null);
    }

    public static NewPartyViewState error(@NonNull String error) {
        return new NewPartyViewState(ERROR, null, error, null);
    }

    public static NewPartyViewState added(@NonNull MemberModel data) {
        return new NewPartyViewState(ADDED, null, null, data);
    }

    public static NewPartyViewState removed(@NonNull MemberModel data) {
        return new NewPartyViewState(REMOVED, null, null, data);
    }
}
