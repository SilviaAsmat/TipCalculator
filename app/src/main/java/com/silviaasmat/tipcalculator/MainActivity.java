package com.silviaasmat.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText tipPercent = findViewById(R.id.tipPercentageInput);
        tipPercent.setText("18");
        ViewModelFactory factory = new ViewModelFactory();
        viewModel = factory.create(MainActivityViewModel.class);
        viewModel.result.observe(this, tipAmount -> {
            Log.v("SAA", tipAmount);
            updateTipTotal(tipAmount);
        }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        observeBillTotal();
        observeTipPercent();
    }

    private void observeBillTotal() {
        EditText input = findViewById(R.id.billTotalInput);
        input.addTextChangedListener(new BillTotalListener());
    }

    private void observeTipPercent() {
        EditText input = findViewById(R.id.tipPercentageInput);
        input.addTextChangedListener(new TipPercentListener());
    }

    private void updateTipTotal(String total) {
        Log.v("SAA", "total: "+ total);
        TextView tipTotal = findViewById(R.id.tipTotal);
        tipTotal.setText("Tip Total: $" + total);
    }

    private void updateTipTotal(double total) {
        updateTipTotal(""+total);
    }

    class BillTotalListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String currentValue = editable.toString();
            Log.v("SAA", currentValue);
            EditText tipPercent = findViewById(R.id.tipPercentageInput);
            String tipPercentString = tipPercent.getEditableText().toString();
            boolean isTipPercentEmpty = tipPercentString.equals("");
            boolean isBillTotalEmpty = currentValue.equals("");
            if (isTipPercentEmpty || isBillTotalEmpty) {
                //do nothing
            } else {
                int tipPercentNumber = Integer.parseInt(tipPercentString);
                double billTotalNumber = Double.parseDouble(currentValue);
                double total = tipPercentNumber * billTotalNumber/100;
                updateTipTotal(total);
            }
        }
    }

    class TipPercentListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String currentValue = editable.toString();
            Log.v("SAA", currentValue);
            EditText billTotal = findViewById(R.id.billTotalInput);
            String billTotalString = billTotal.getEditableText().toString();
            boolean isBillTotalStringEmpty = billTotalString.equals("");
            boolean isTipPercentEmpty = currentValue.equals("");
            if (isBillTotalStringEmpty || isTipPercentEmpty) {
                //do nothing
            } else {
                int tipPercentNumber = Integer.parseInt(currentValue);
                double billTotalNumber = Double.parseDouble(billTotalString);
                double total = tipPercentNumber * billTotalNumber/100;
                updateTipTotal(total);
            }
        }
    }
}