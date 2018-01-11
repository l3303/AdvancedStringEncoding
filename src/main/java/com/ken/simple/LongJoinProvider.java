package com.ken.simple;

import com.ken.ValueJoinFormat;
import com.ken.exception.ValueCountNotMatchException;
import com.ken.exception.ValueJoinException;

public class LongJoinProvider {

    private static final int DIGITS_LIMIT = Long.SIZE - 1;

    public static boolean validate(ValueJoinFormat format) {
        int totalDigits = 0;
        for (int digits : format.encodeDigitList()) {
            totalDigits += digits;
            if (totalDigits > DIGITS_LIMIT) {
                return false;
            }
        }
        return true;
    }

    public static long join (ValueJoinFormat format, long... valueList) throws ValueJoinException {
        int elementCount = valueList.length;
        if (format.getElementCount() != elementCount) {
            throw new ValueCountNotMatchException(elementCount, valueList.length);
        }

        int[] encodeDigitList = format.encodeDigitList();
        short result = 0;
        for (int i = 0; i < elementCount; i++) {
            long value = valueList[i];
            int digits = encodeDigitList[i];
            result <<= digits;
            result += value;
        }
        return result;
    }
}
