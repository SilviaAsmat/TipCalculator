package com.silviaasmat.tipcalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData _result = new MutableLiveData();
    public LiveData<String> result = _result;

    public void updateBillTotal(String billTotal) {

    }

    public void updateTipPercent(String tipPercent) {

    }

}


