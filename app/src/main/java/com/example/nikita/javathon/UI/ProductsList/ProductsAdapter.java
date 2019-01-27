package com.example.nikita.javathon.UI.ProductsList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.UI.PartyList.PartyListModel;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private List<ProductsModel> productsList;
    private Context context;

    public ProductsAdapter(Context context) {
        this.context = context;
    }
    interface ItemSelected{
        void viewProductDetails(ProductsModel product);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.products_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.price.setText(productsList.get(i).Price);
        viewHolder.name.setText(productsList.get(i).Name);
        viewHolder.itemView.setOnClickListener(view ->
                ((ProductsActivity) context).viewProductDetails(productsList.get(i)));
    }

    void setProducts(List<ProductsModel> products){
        this.productsList = products;
    }

    void setProduct(ProductsModel productsModel){
        productsList.add(productsModel);
        notifyItemInserted(productsList.size());
    }

    @Override
    public int getItemCount() {
        if(productsList == null){
            return 0;
        }else{
            return productsList.size();
        }
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
