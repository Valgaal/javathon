package com.example.nikita.javathon.Data;

import com.example.nikita.javathon.UI.ProductDetails.ProductDetailsListModel;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductsApi {

    @GET("")
    Observable<List<ProductsModel>> getProducts(
            @Header("Authorization") String token,
            @Query("room_id") int roomId);

    @GET("")
    Observable<List<ProductDetailsListModel>> getProductDetails(
            @Header("Authorization") String token,
            @Query("product_id") int productId);

    @POST("")
    Observable<ResponseBody> addProduct(
            @Header("Authorization") String token,
            @Body ProductsModel productsModel);

}
