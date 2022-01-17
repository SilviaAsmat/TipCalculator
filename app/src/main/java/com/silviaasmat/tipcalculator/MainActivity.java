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
            String formatted = "$" + currentValue;
            EditText input = findViewById(R.id.billTotalInput);
            input.removeTextChangedListener(this);
            input.setText(formatted);
            input.setSelection(formatted.length());
            Log.v("SAA", currentValue);
            viewModel.updateBillTotal(currentValue);
            input.addTextChangedListener(this);
            String s = formatted;
            if (s.contains(".")) {
                int indexOfDecimalPoint;
                for(int index = 0; index < s.length(); index++) {
                    if (s.charAt(index) == '.') {
                        // $123.75  s.length=7   indexOfDecimalPoint=4
                        // $123.75869  s.length=10   indexOfDecimalPoint=4
                        // $123.7  s.length=6   indexOfDecimalPoint=4
                        indexOfDecimalPoint = index;
                        int maxValidLength = indexOfDecimalPoint + 3;
                        if (s.length() <= maxValidLength) {
                            // do nothing
                        } else {
                            String substring = s.substring(0, maxValidLength);
                            input.removeTextChangedListener(this);
                            input.setText(substring);
                            input.setSelection(substring.length());
                            input.addTextChangedListener(this);
                        }
                    }
                }
            } else {

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