package com.example.nikita.javathon.Data;

import com.example.nikita.javathon.UI.NewPartyActivity.MemberModel;
import com.example.nikita.javathon.UI.PartyList.PartyListModel;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MembersApi {

    @POST("")
    Observable<ResponseBody> addParty(
                    @Header("Authorization") String token,
                    @Body PartyListModel partyListModel);

    @POST("")
    Observable<ResponseBody> addMember(
            @Header("Authorization") String token,
            @Body MemberModel memberModel);

    @POST("")
    Observable<ResponseBody> removeMember(
            @Header("Authorization") String token,
            @Body MemberModel memberModel);

    @GET("")
    Observable<List<PartyListModel>> getPartyList(
            @Header("Authorization") String token,
            @Query("member_id") int memberId);

    @GET("")
    Observable<List<MemberModel>> getMembers(
            @Header("Authorization") String token,
            @Query("search") String search);
}
