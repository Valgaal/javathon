package com.example.nikita.javathon.UI.PartyList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.Data.Repository;

import java.net.UnknownHostException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class PartyListViewModel extends AndroidViewModel {

    MutableLiveData<PartyListViewState> stateLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Repository mRepository;

    public PartyListViewModel(@NonNull Application application) {
        super(application);
        mRepository  = Repository.getInstance();
    }

    void getParties(int id){
        stateLiveData.setValue(PartyListViewState.loading());
        disposable.add(mRepository.getPartyList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        partyList -> {
                            if (partyList.size() != 0) {
                                stateLiveData.setValue(PartyListViewState.success(partyList));
                            } else {
                                stateLiveData.setValue(PartyListViewState.error(getApplication().getResources().getString(R.string.not_found)));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(PartyListViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(PartyListViewState.error(throwable.getMessage()));
                            }
                        }
                ));

    }
    @Override
    protected void onCleared() {
        disposable.clear();
    }

    void addParty(PartyListModel partyListModel){
        stateLiveData.setValue(PartyListViewState.loading());
        disposable.add(mRepository.addParty(partyListModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                                stateLiveData.setValue(PartyListViewState.added(partyListModel));
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(PartyListViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(PartyListViewState.error(throwable.getMessage()));
                            }
                        }
                ));
    }
}
