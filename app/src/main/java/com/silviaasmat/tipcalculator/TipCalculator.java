package com.silviaasmat.tipcalculator;

public class TipCalculator {
    private double billTotal;
    private int tipPercentage;

    public void setBillTotal(double billTotal) {
        boolean isNegative = billTotal < 0;
        if (isNegative) {
            // Do nothing
        } else {
            this.billTotal = billTotal;
        }
    }

    public void setTipPercentage(int tipPercentage) {
        boolean isNegative = tipPercentage < 0;
        if (isNegative) {
            // Do nothing
        } else {
            this.tipPercentage = tipPercentage;
        }
    }

    /**
     * Calculates tip and limits to two decimal places
     * @return
     */
    public double calculateTip() {
        double result = billTotal * tipPercentage / 100;
        StringUtils stringUtils = new StringUtils();
        String formatted = stringUtils.limitDecimalStringToTwoDecimalPoints("" + result);
        return Double.parseDouble(formatted);
    }

    public double calculateGrandTotal() {
        double grandTotal = billTotal + calculateTip();
        return grandTotal;
    }
}
