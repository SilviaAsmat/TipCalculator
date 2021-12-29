package com.silviaasmat.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText tipPercent = findViewById(R.id.tipPercentageInput);
        tipPercent.setText("18");

    }

    @Override
    protected void onStart() {
        super.onStart();
        observeBillTotal();
    }

    private void observeBillTotal() {
        EditText input = findViewById(R.id.billTotalInput);
        input.addTextChangedListener(new BillTotalListener());
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
            if (isTipPercentEmpty) {
                //do nothing
            } else {
                int tipPercentNumber = Integer.parseInt(tipPercentString);
                double billTotalNumber = Double.parseDouble(currentValue);
                double total = tipPercentNumber * billTotalNumber/100;
                Log.v("SAA", "total: "+ total);
                TextView tipTotal = findViewById(R.id.tipTotal);
                tipTotal.setText("Tip Total: $" + total);
            }

        }
    }
}