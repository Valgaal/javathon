package com.example.nikita.javathon.UI.NewPartyActivity;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.utils.Utils;

import java.util.List;

public class NewPartyActivity  extends AppCompatActivity implements
        SearchAdapter.SearchCallback,
        NewPartyAdapter.RemoveCallback{
    private NewPartyViewModel mViewModel;
    private NewPartyAdapter newPartyAdapter;
    private SearchAdapter searchAdapter;
    private ProgressBar progressBar;

    public static final String RESULT = "result";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_party);
        mViewModel = ViewModelProviders.of(this).get(NewPartyViewModel.class);
        mViewModel.stateLiveData.observe(this, this::displayState);
        EditText searchEditText = findViewById(R.id.searchEditText);
        progressBar = findViewById(R.id.progressBar);
        setRv();

        findViewById(R.id.searchButton).setOnClickListener(view1 -> {
            Utils.hideKeyboardFrom(this, view1);
            mViewModel.getMembers(searchEditText.getText().toString());
        });

        findViewById(R.id.doneButton).setOnClickListener(view -> {
            sendResult();

        });
    }

    private void setRv(){
        RecyclerView searchRv = findViewById(R.id.searchRv);
        searchRv.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this);
        searchRv.setAdapter(searchAdapter);

        RecyclerView addedRv = findViewById(R.id.addedPeopleRv);
        addedRv.setLayoutManager(new LinearLayoutManager(this));
        newPartyAdapter = new NewPartyAdapter(this);
        addedRv.setAdapter(newPartyAdapter);
    }


    private void displayState(NewPartyViewState newPartyViewState) {
        switch (newPartyViewState.status) {
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                searchAdapter.setMembers(newPartyViewState.data);
                break;
            case ERROR:
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, newPartyViewState.error, Toast.LENGTH_SHORT).show();
                break;
            case ADDED:
                progressBar.setVisibility(View.GONE);
                newPartyAdapter.setMember(newPartyViewState.singleData);
                break;
            case REMOVED:
                progressBar.setVisibility(View.GONE);
                newPartyAdapter.removeMember(newPartyViewState.singleData);
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void sendResult(){
        EditText nameEt = findViewById(R.id.editText);
        String name = nameEt.getText().toString();
        Intent returnIntent = new Intent();
        //преобразовать в PartyList
        List<MemberModel> arrayList = newPartyAdapter.getMembers();
        returnIntent.putExtra(result, newPartyModel);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void searchClick(MemberModel memberModel) {
        mViewModel.addMember(memberModel);
    }

    @Override
    public void remove(MemberModel memberModel) {
        mViewModel.removeMember(memberModel);
    }
}
