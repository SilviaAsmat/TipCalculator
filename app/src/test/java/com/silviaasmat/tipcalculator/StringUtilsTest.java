package com.silviaasmat.tipcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class StringUtilsTest {
    StringUtils utils = new StringUtils();

    @Test
    public void test_large_number_with_many_decimal_points() {
        // Arrange
        String data = "12345.642345";

        // Act
        String actual = utils.convertDecimalToCurrency(data);

        // Assert
        String expected = "$12345.64";
        assertEquals(expected, actual);
    }

    @Test
    public void test_large_number_with_two_decimal_points() {
        // Arrange
        String data = "12345.64";

        // Act
        String actual = utils.convertDecimalToCurrency(data);

        // Assert
        String expected = "$12345.64";
        assertEquals(expected, actual);
    }

    @Test
    public void test_number_with_only_decimal_points() {
        // Arrange
        String data = ".642345";

        // Act
        String actual = utils.convertDecimalToCurrency(data);

        // Assert
        String expected = "$.64";
        assertEquals(expected, actual);
    }

    @Test
    public void test_large_number_without_decimal_points() {
        // Arrange
        String data = "12345.";

        // Act
        String actual = utils.convertDecimalToCurrency(data);

        // Assert
        String expected = "$12345.";
        assertEquals(expected, actual);
    }

    @Test
    public void test_small_number_with_one_decimal_points() {
        // Arrange
        String data = "1.6";

        // Act
        String actual = utils.convertDecimalToCurrency(data);

        // Assert
        String expected = "$1.6";
        assertEquals(expected, actual);
    }

    @Test
    public void test_empty_number() {
        // Arrange
        String data = "";

        // Act
        String actual = utils.convertDecimalToCurrency(data);

        // Assert
        String expected = "$";
        assertEquals(expected, actual);
    }


}

