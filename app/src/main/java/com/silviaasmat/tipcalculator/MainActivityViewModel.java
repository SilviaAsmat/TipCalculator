package com.silviaasmat.tipcalculator;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData _result = new MutableLiveData();
    public LiveData<String> result = _result;

    private MutableLiveData _errorMessage = new MutableLiveData();
    public LiveData<String> errorMessage = _errorMessage;

    private MutableLiveData _grandTotal = new MutableLiveData();
    public LiveData<String> grandTotal = _grandTotal;

    private TipCalculator tipCalculator = new TipCalculator();

    public void updateBillTotal(String billTotal) {
        if (billTotal == null || billTotal.equals("")) {
            // do nothing
        } else {
            double converted = Double.parseDouble(billTotal);
            tipCalculator.setBillTotal(converted);
            calculateTip();
            calculateGrandTotal();
        }
    }

    public void updateTipPercent(String tipPercent) {
        if (tipPercent == null || tipPercent.equals("")) {
            // do nothing
        } else {
            try {
                int converted = Integer.parseInt(tipPercent);
                tipCalculator.setTipPercentage(converted);
                calculateTip();
                calculateGrandTotal();
            } catch(Exception e) {
                Log.v("SAA", e.getLocalizedMessage());
                _errorMessage.setValue("Invalid Tip Percent.");
            }
        }
    }

    private void calculateTip() {
        double result = tipCalculator.calculateTip();
        _result.setValue("Tip Total:\n$" + result);
        _errorMessage.setValue(null);
    }

    private void calculateGrandTotal() {
        double result = tipCalculator.calculateGrandTotal();
        _grandTotal.setValue("Grand Total:\n$" + result);
    }

}


