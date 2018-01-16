package com.ken.simple;

import com.ken.ValueJoinFormat;
import com.ken.exception.ValueCountNotMatchException;
import com.ken.exception.ValueJoinException;
import com.ken.exception.ValueJoinOutOfRangeException;

public class IntegerJoinProvider {

    private static final int DIGITS_LIMIT = Integer.SIZE;

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

    public static int join (ValueJoinFormat format, int... valueList) throws ValueJoinException {
        int elementCount = valueList.length;
        if (format.getElementCount() != elementCount) {
            throw new ValueCountNotMatchException(format.getElementCount(), valueList.length);
        }

        int[] encodeDigitList = format.encodeDigitList();
        int result = 0;
        for (int i = 0; i < elementCount; i++) {
            result = join(result, encodeDigitList[i], valueList[i]);
        }
        return result;
    }

    public static int[] split(ValueJoinFormat format, int value) {
        int[] digitList = format.encodeDigitList();
        int[] list = new int[digitList.length];
        for (int i = digitList.length; i > 0; i--) {
            int digits = digitList[i - 1];
            list[i - 1] = value & ((1 << digits) - 1);
            value >>>= digits;
        }
        return list;
    }

    private static int join(int origin, int digits, int value) {
        origin <<= digits;
        origin += value;
        return origin;
    }

    public class Builder {
        private int result = 0;
        private int usedDigits = 0;

        public int append(int digits, int value) throws ValueJoinException {
            usedDigits += digits;
            if (usedDigits > DIGITS_LIMIT) {
                throw new ValueJoinOutOfRangeException(DIGITS_LIMIT, usedDigits);
            }
            int limit = 1 << digits - 1;
            if (value > limit) {
                throw new ValueJoinOutOfRangeException(limit, value);
            }
            result = join(result, digits, value);
            return result;
        }
    }
}
