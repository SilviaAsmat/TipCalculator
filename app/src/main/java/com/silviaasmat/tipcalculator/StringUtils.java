package com.silviaasmat.tipcalculator;

public class StringUtils {
    /**
     * converts decimal value to currency with dollar sign and limited to two decimal points
     * @return formatted string
     */
    public String convertDecimalToCurrency(String s) {
        return "$" + limitDecimalStringToTwoDecimalPoints(s);
    }


    private String limitDecimalStringToTwoDecimalPoints(String s) {
        if (s.contains(".")) {
            int indexOfDecimalPoint;
            for(int index = 0; index < s.length(); index++) {
                if (s.charAt(index) == '.') {
                    indexOfDecimalPoint = index;
                    int maxValidLength = indexOfDecimalPoint + 3;
                    if (s.length() <= maxValidLength) {
                        // do nothing
                    } else {
                        String substring = s.substring(0, maxValidLength);
                        return substring;
                    }
                }
            }
        }
        return s;
    }
}


