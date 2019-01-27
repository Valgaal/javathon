package com.example.nikita.javathon.UI.ProductDetails;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikita.javathon.UI.ProductsList.ListViewState;
import com.example.nikita.javathon.R;

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String ID = "Id";

    private ProductDetailsAdapter mAdapter;
    private ProductDetailsViewModel mViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        int productId = getIntent().getExtras().getInt(ID, 0);
        mViewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);
        setRv();
        progressBar = findViewById(R.id.progressBar);
        mViewModel.stateLiveData.observe(this, this::displayState);
        mViewModel.getProductDetails(productId);

    }

    private void setRv(){
        RecyclerView rv = findViewById(R.id.listRv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ProductDetailsAdapter();
        rv.setAdapter(mAdapter);
    }

    private void displayState(ProductDetailsViewState viewState) {
        switch (viewState.status) {
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                mAdapter.setPrices(viewState.data);
                break;
            case ERROR:
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, viewState.error, Toast.LENGTH_SHORT).show();
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }
}
