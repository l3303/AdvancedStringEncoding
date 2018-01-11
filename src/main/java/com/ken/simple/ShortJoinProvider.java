package com.ken.simple;

import com.ken.ValueJoinFormat;
import com.ken.exception.ValueCountNotMatchException;
import com.ken.exception.ValueJoinException;

public class ShortJoinProvider {

    private static final int DIGITS_LIMIT = Short.SIZE - 1;

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

    public static short join (ValueJoinFormat format, short... valueList) throws ValueJoinException {
        int elementCount = valueList.length;
        if (format.getElementCount() != elementCount) {
            throw new ValueCountNotMatchException(elementCount, valueList.length);
        }

        int[] encodeDigitList = format.encodeDigitList();
        short result = 0;
        for (int i = 0; i < elementCount; i++) {
            short value = valueList[i];
            int digits = encodeDigitList[i];
            result <<= digits;
            result += value;
        }
        return result;
    }
}
