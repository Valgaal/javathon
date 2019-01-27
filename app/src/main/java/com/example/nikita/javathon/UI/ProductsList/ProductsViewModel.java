package com.example.nikita.javathon.UI.ProductsList;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.Repository;
import com.example.nikita.javathon.UI.PartyList.PartyListViewState;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ProductsViewModel extends AndroidViewModel {

    MutableLiveData<ListViewState> stateLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Repository mRepository;

    public ProductsViewModel(@NonNull Application application) {
        super(application);
        mRepository  = Repository.getInstance();
    }

    void getProducts(){
        stateLiveData.setValue(ListViewState.loading());
        disposable.add(mRepository.getProducts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        products -> {
                            if (products.size() != 0) {
                                stateLiveData.setValue(ListViewState.success(products));
                            } else {
                                stateLiveData.setValue(ListViewState.error(getApplication().getResources().getString(R.string.not_found)));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(ListViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(ListViewState.error(throwable.getMessage()));
                            }
                        }
                ));

    }
    @Override
    protected void onCleared() {
        disposable.clear();
    }

    void sendMoney(){
        disposable.add(mRepository.sendMoney()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            stateLiveData.setValue(ListViewState.sent(getApplication().getResources().getString(R.string.success)));
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(ListViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(ListViewState.error(throwable.getMessage()));
                            }
                        }
                ));
    }

    void addProduct(ProductsModel productsModel){
        disposable.add(mRepository.addProduct(productsModel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            stateLiveData.setValue(ListViewState.added(productsModel));
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(ListViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(ListViewState.error(throwable.getMessage()));
                            }
                        }
                ));
    }
}
