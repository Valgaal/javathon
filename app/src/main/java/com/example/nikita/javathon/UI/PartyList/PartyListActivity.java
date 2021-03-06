package com.example.nikita.javathon.UI.PartyList;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.UI.NewPartyActivity.NewPartyActivity;
import com.example.nikita.javathon.UI.ProductsList.ProductsActivity;


public class PartyListActivity extends AppCompatActivity implements PartyListAdapter.PartiesCallback {

    public static final String USER_ID = "User_Id";
    public static final String PARTY_ID = "Party_Id";

    private PartyListAdapter mAdapter;
    private PartyListViewModel mViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_list);
        int productId = getIntent().getExtras().getInt(USER_ID, 0);
        mViewModel = ViewModelProviders.of(this).get(PartyListViewModel.class);
        progressBar = findViewById(R.id.progressBar);
        setRv();
        mViewModel.stateLiveData.observe(this, this::displayState);
        mViewModel.getParties(productId);

    }

    private void setRv(){
        RecyclerView rv = findViewById(R.id.listRv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PartyListAdapter(this);
        rv.setAdapter(mAdapter);
        findViewById(R.id.fab).setOnClickListener(view -> {
            Intent intent = new Intent(PartyListActivity.this, NewPartyActivity.class);
            startActivityForResult(intent,0);
        });
    }

    private void displayState(PartyListViewState viewState) {
        switch (viewState.status) {
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                mAdapter.setParties(viewState.data);
                break;
            case ERROR:
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, viewState.error, Toast.LENGTH_SHORT).show();
                break;
            case ADDED:
                progressBar.setVisibility(View.GONE);
                mAdapter.setParty(viewState.singleData);
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void partyClick(int partyId) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra(PartyListActivity.PARTY_ID, partyId);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            PartyListModel partyListModel =(PartyListModel) data.getSerializableExtra(NewPartyActivity.RESULT);
            mViewModel.addParty(partyListModel);
        }
    }
}
