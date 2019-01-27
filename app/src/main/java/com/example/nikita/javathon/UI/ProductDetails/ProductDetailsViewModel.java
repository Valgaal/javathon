package com.example.nikita.javathon.UI.ProductDetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.nikita.javathon.R;
import com.example.nikita.javathon.Data.Repository;

import java.net.UnknownHostException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ProductDetailsViewModel extends AndroidViewModel {

    MutableLiveData<ProductDetailsViewState> stateLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Repository mRepository;

    public ProductDetailsViewModel(@NonNull Application application) {
        super(application);
        mRepository  = Repository.getInstance();
    }

    void getProductDetails(int id){
        stateLiveData.setValue(ProductDetailsViewState.loading());
        disposable.add(mRepository.getProductDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productDetails -> {
                            if (productDetails.size() != 0) {
                                stateLiveData.setValue(ProductDetailsViewState.success(productDetails));
                            } else {
                                stateLiveData.setValue(ProductDetailsViewState.error(getApplication().getResources().getString(R.string.not_found)));
                            }
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                stateLiveData.setValue(ProductDetailsViewState.error(getApplication().getResources().getString(R.string.internet_error)));
                            } else {
                                stateLiveData.setValue(ProductDetailsViewState.error(throwable.getMessage()));
                            }
                        }
                ));

    }
    @Override
    protected void onCleared() {
        disposable.clear();
    }
}
