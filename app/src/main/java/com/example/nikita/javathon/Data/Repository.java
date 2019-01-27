package com.example.nikita.javathon.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nikita.javathon.UI.Auth.SignInRequest;
import com.example.nikita.javathon.UI.NewPartyActivity.MemberModel;
import com.example.nikita.javathon.UI.PartyList.PartyListModel;
import com.example.nikita.javathon.UI.ProductDetails.ProductDetailsListModel;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Repository {

    private static Repository INSTANCE;

    private static final String BASE_URL = "http://javathon.kolebor.ru:8331/";

    private MembersApi membersApi;
    private ProductsApi productsApi;
    private SharedPreferences preferences;

    private Repository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        membersApi = retrofit.create(MembersApi.class);
        productsApi = retrofit.create(ProductsApi.class);
        preferences = context.getSharedPreferences();
    }

    public static Repository getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(context);
                }
            }
        }
        return INSTANCE;
    }

    public static Repository getInstance() {
        return INSTANCE;
    }

    public Observable<List<ProductsModel>> getProducts() {
        return productsApi.getProducts();
    }

    public Observable<List<ProductDetailsListModel>> getProductDetails(int id) {
        return productsApi.getProductDetails();
    }

    public Observable<List<PartyListModel>> getPartyList(int id) {
        return membersApi.getPartyList(id);
    }

    public Observable<ResponseBody> sendMoney() {
        return productsApi.sendMoney();
    }

    public Observable<ResponseBody> addProduct(ProductsModel productsModel) {
        return productsApi.addProduct(productsModel);
    }

    public Observable<ResponseBody> addParty(PartyListModel partyListModel) {
        return membersApi.addParty(partyListModel);
    }

    public Observable<ResponseBody> addMember(MemberModel memberModel) {
        return membersApi.addMember(memberModel);
    }

    public Observable<ResponseBody> removeMember(MemberModel memberModel) {
        return membersApi.removeMember(memberModel);
    }

    public Observable<List<MemberModel>> getMembers(String searchText) {
        return membersApi.getMembers(searchText);
    }

    public Call<String> postSignInUser(SignInRequest user){
       return membersApi.postSignInUser(user);
    }
}
