package com.example.nikita.javathon.UI.ProductsList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.UI.ProductDetails.ProductDetailsActivity;

public class ProductsActivity  extends AppCompatActivity implements
        ProductsAdapter.ItemSelected,
        FragmentDialogNewProduct.DialogCallback {

    private ProductsViewModel mViewModel;
    private ProductsAdapter mAdapter;
    private Button sendMoneyButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);
        sendMoneyButton = findViewById(R.id.sendMoneyButton);
        sendMoneyButton.setOnClickListener(view -> mViewModel.sendMoney());
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.addProductButton).setOnClickListener(view -> addProduct());
        mViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        setRv();
        mViewModel.stateLiveData.observe(this, this::displayState);
        mViewModel.getProducts();
    }

    private void setRv(){
        RecyclerView listRv = findViewById(R.id.listRv);
        listRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProductsAdapter(this);
        listRv.setAdapter(mAdapter);
    }

    private void displayState(ListViewState viewState) {
        switch (viewState.status) {
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                mAdapter.setProducts(viewState.data);
                break;
            case ERROR:
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, viewState.message, Toast.LENGTH_SHORT).show();
                break;
            case ADDED:
                progressBar.setVisibility(View.GONE);
                mAdapter.setProduct(viewState.singleData);
                break;
            case SENT:
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, viewState.message, Toast.LENGTH_SHORT).show();
                sendMoneyButton.setVisibility(View.GONE);
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void addProduct(){
        FragmentDialogNewProduct dialog = FragmentDialogNewProduct.newInstance(this);
        dialog.show(getSupportFragmentManager(),"Dialog");
    }

    @Override
    public void viewProductDetails(ProductsModel product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.ID, product.getId());
        startActivity(intent);
    }

    @Override
    public void productAdded(ProductsModel productsModel) {
        mViewModel.addProduct(productsModel);
    }
}
