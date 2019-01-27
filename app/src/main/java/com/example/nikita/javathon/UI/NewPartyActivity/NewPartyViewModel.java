package com.example.nikita.javathon.UI.NewPartyActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.Data.Repository;

import java.net.UnknownHostException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class NewPartyViewModel extends AndroidViewModel {

    MutableLiveData<NewPartyViewState> stateLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Repository mRepository;

    public NewPartyViewModel(@NonNull Application application) {
        super(application);
        mRepository  = Repository.getInstance();
    }

    void getMembers(String searchText) {
        stateLiveData.setValue(NewPartyViewState.loading());
        disposable.add(mRepository.getMembers(searchText)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        members -> {
                            if (members.size() != 0) {
                                stateLiveData.setValue(NewPartyViewState.success(members));
                            } else {
                                stateLiveData.setValue(NewPartyViewState.error(getApplication().getResources().getString(R.string.not_found)));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(NewPartyViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(NewPartyViewState.error(throwable.getMessage()));
                            }
                        }
                ));

    }
    @Override
    protected void onCleared() {
        disposable.clear();
    }

    void addMember(MemberModel addMember){
        stateLiveData.setValue(NewPartyViewState.loading());
        disposable.add(mRepository.addMember(addMember)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            stateLiveData.setValue(NewPartyViewState.added(addMember));
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(NewPartyViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(NewPartyViewState.error(throwable.getMessage()));
                            }
                        }
                ));
    }

    void removeMember(MemberModel member){
        stateLiveData.setValue(NewPartyViewState.loading());
        disposable.add(mRepository.removeMember(member)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            stateLiveData.setValue(NewPartyViewState.removed(member));
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(NewPartyViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(NewPartyViewState.error(throwable.getMessage()));
                            }
                        }
                ));
    }
}
