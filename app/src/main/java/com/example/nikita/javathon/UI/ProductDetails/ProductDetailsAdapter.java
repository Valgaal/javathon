package com.example.nikita.javathon.UI.ProductDetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.UI.ProductsList.ProductsModel;

import java.util.List;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder> {

    private List<ProductDetailsListModel> productDetailsList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_details_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.price.setText(productDetailsList.get(i).Price);
        viewHolder.name.setText(productDetailsList.get(i).Name);

    }

    @Override
    public int getItemCount() {
        if(productDetailsList == null){
            return 0;
        }else{
            return productDetailsList.size();
        }
    }

    void setPrices(List<ProductDetailsListModel> products){
        this.productDetailsList = products;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView price;
        private TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.priceTv);
            name = itemView.findViewById(R.id.nameTv);
        }
    }

}
