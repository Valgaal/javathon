package com.example.nikita.javathon;

import android.content.Context;

import com.example.nikita.javathon.UI.NewPartyActivity.MemberModel;
import com.example.nikita.javathon.UI.PartyList.PartyListModel;
import com.example.nikita.javathon.UI.ProductDetails.ProductDetailsListModel;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


public class Repository {

    private static Repository INSTANCE;

    private Repository() {

    }

    public static Repository getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (Repository.class){
                if(INSTANCE == null){
                    INSTANCE = new Repository();
                }
            }
        }
        return INSTANCE;
    }

    public  static Repository getInstance(){
        return INSTANCE;
    }

    public Observable<List<ProductsModel>> getProducts(){

    }

    public Observable<List<ProductDetailsListModel>> getProductDetails(int id){

    }

    public Observable<List<PartyListModel>> getPartyList(int id){

    }

    public Observable<ResponseBody> sendMoney(){

    }

    public Observable<ResponseBody> addProduct(ProductsModel productsModel){

    }

    public Observable<ResponseBody> addParty(PartyListModel productsModel){

    }

    public Observable<ResponseBody> addMember(MemberModel memberModel){

    }

    public Observable<ResponseBody> removeMember(MemberModel memberModel){

    }

    public Observable<List<MemberModel>> getMembers(String searchText){

    }
}
