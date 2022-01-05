package com.silviaasmat.tipcalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData _result = new MutableLiveData();
    public LiveData<String> result = _result;
    private TipCalculator tipCalculator = new TipCalculator();


    public void updateBillTotal(String billTotal) {
        if (billTotal == null || billTotal.equals("")) {
            // do nothing
        } else {
            double converted = Double.parseDouble(billTotal);
            tipCalculator.setBillTotal(converted);
            calculateTip();
        }
    }

    public void updateTipPercent(String tipPercent) {
        if (tipPercent == null || tipPercent.equals("")) {
            // do nothing
        } else {
            int converted = Integer.parseInt(tipPercent);
            tipCalculator.setTipPercentage(converted);
            calculateTip();
        }
    }

    private void calculateTip() {
        double result = tipCalculator.calculateTip();
        _result.setValue("Tip Total: $" + result);
    }

}


