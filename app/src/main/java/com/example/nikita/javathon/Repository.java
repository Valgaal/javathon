package com.example.nikita.javathon;

import android.content.Context;

import com.example.nikita.javathon.UI.ProductDetails.ProductDetailsListModel;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

import io.reactivex.Observable;


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

    public void sendMoney(){

    }

    public void addProduct(ProductsModel productsModel){

    }
}
