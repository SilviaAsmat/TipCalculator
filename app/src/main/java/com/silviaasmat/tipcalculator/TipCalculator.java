package com.silviaasmat.tipcalculator;

public class TipCalculator {
    private double billTotal;
    private int tipPercentage;

    public void setBillTotal(double billTotal) {
        this.billTotal = billTotal;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public double calculateTip() {
        double result = billTotal * tipPercentage / 100;
        return result;
    }
}
