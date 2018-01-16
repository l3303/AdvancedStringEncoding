package com.ken.simple;

import com.ken.ValueJoinFormat;
import com.ken.exception.ValueCountNotMatchException;
import com.ken.exception.ValueJoinException;
import com.ken.exception.ValueJoinOutOfRangeException;

public class LongJoinProvider {

    private static final int DIGITS_LIMIT = Long.SIZE;

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
            throw new ValueCountNotMatchException(format.getElementCount(), valueList.length);
        }

        int[] encodeDigitList = format.encodeDigitList();
        long result = 0;
        for (int i = 0; i < elementCount; i++) {
            result = join(result, encodeDigitList[i], valueList[i]);
        }
        return result;
    }

    public static long[] split(ValueJoinFormat format, long value) {
        int[] digitList = format.encodeDigitList();
        long[] list = new long[digitList.length];
        for (int i = digitList.length; i > 0; i--) {
            int digits = digitList[i - 1];
            list[i - 1] = value & ((1L << digits) - 1L);
            value >>>= digits;
        }
        return list;
    }

    private static long join(long origin, int digits, long value) {
        origin <<= digits;
        origin += value;
        return origin;
    }

    public static class Builder {
        private long result = 0;
        private int usedDigits = 0;

        public long append(int digits, long value) throws ValueJoinException {
            usedDigits += digits;
            if (usedDigits > DIGITS_LIMIT) {
                throw new ValueJoinOutOfRangeException(DIGITS_LIMIT, usedDigits);
            }
            long limit = 1L << digits - 1L;
            if (value > limit) {
                throw new ValueJoinOutOfRangeException(limit, value);
            }
            result = join(result, digits, value);
            return result;
        }
    }
}
