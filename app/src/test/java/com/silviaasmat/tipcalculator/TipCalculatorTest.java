package com.silviaasmat.tipcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TipCalculatorTest {

    @Test
    public void test_calculate_tip_default_values() {
        // Arrange
        TipCalculator tipCalculator = new TipCalculator();

        // Act
        double actual = tipCalculator.calculateTip();

        // Assert
        assertEquals(0.0, actual,0.0);
    }

    @Test
    public void test_calculate_tip_random_values() {
        // Arrange
        TipCalculator tipCalculator = new TipCalculator();
        tipCalculator.setBillTotal(86.0);
        tipCalculator.setTipPercentage(18);

        // Act
        double actual = tipCalculator.calculateTip();

        // Assert
        assertEquals(15.48, actual,0.0);
    }

    @Test
    public void test_calculate_tip_negative_bill_total() {
        // Arrange
        TipCalculator tipCalculator = new TipCalculator();
        tipCalculator.setBillTotal(-86.0);
        tipCalculator.setTipPercentage(10);

        // Act
        double actual = tipCalculator.calculateTip();

        // Assert
        assertEquals(0, actual,0.0);
    }

    @Test
    public void test_calculate_tip_negative_tip_percentage() {
        // Arrange
        TipCalculator tipCalculator = new TipCalculator();
        tipCalculator.setBillTotal(86.0);
        tipCalculator.setTipPercentage(-10);

        // Act
        double actual = tipCalculator.calculateTip();

        // Assert
        assertEquals(0, actual,0.0);
    }


}
