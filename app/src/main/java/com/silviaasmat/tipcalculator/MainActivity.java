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
            viewModel.updateBillTotal(currentValue);
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
            viewModel.updateTipPercent(currentValue);
        }
    }
}