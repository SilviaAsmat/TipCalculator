package com.silviaasmat.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewModel();
        setPresetButtonsClickListeners();
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
        tipTotal.setText(total);
    }

    private void setPresetButtonsClickListeners() {
        Button preset15button = findViewById(R.id.preset15Button);
        preset15button.setOnClickListener(view -> {
            EditText input = findViewById(R.id.tipPercentageInput);
            input.setText("15");
        });

        Button preset18button = findViewById(R.id.preset18Button);
        preset18button.setOnClickListener(view -> {
            EditText input = findViewById(R.id.tipPercentageInput);
            input.setText("18");
        });

        Button preset20button = findViewById(R.id.preset20Button);
        preset20button.setOnClickListener(view -> {
            EditText input = findViewById(R.id.tipPercentageInput);
            input.setText("20");
        });
    }

    private void setupViewModel() {
        ViewModelFactory factory = new ViewModelFactory();
        viewModel = factory.create(MainActivityViewModel.class);
        viewModel.result.observe(this, tipAmount -> {
                    updateTipTotal(tipAmount);
                }
        );
        viewModel.errorMessage.observe(this, errorMessage -> {
            EditText input = findViewById(R.id.tipPercentageInput);
            input.setError(errorMessage);
                }
        );
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
            String currentValue = editable.toString().replace("$", "");
            StringUtils stringUtils = new StringUtils();
            String formatted = stringUtils.convertDecimalToCurrency(currentValue);
            EditText input = findViewById(R.id.billTotalInput);
            input.removeTextChangedListener(this);
            input.setText(formatted);
            input.setSelection(formatted.length());
            String newBillTotal = formatted.replace("$", "");
            viewModel.updateBillTotal(newBillTotal);
            input.addTextChangedListener(this);
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
            String currentValue = editable.toString().replace("%", "");
            String formatted = currentValue + "%";
            EditText input = findViewById(R.id.tipPercentageInput);
            input.removeTextChangedListener(this);
            input.setText(formatted);
            input.setSelection(formatted.length() - 1);
            Log.v("SAA", currentValue);
            viewModel.updateTipPercent(currentValue);
            input.addTextChangedListener(this);
        }
    }


}