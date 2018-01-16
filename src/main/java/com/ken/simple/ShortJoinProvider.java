package com.ken.simple;

import com.ken.ValueJoinFormat;
import com.ken.exception.ValueCountNotMatchException;
import com.ken.exception.ValueJoinException;
import com.ken.exception.ValueJoinOutOfRangeException;

public class ShortJoinProvider {

    private static final int DIGITS_LIMIT = Short.SIZE;

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
            throw new ValueCountNotMatchException(format.getElementCount(), valueList.length);
        }

        int[] encodeDigitList = format.encodeDigitList();
        short result = 0;
        for (int i = 0; i < elementCount; i++) {
            result = join(result, encodeDigitList[i], valueList[i]);
        }
        return result;
    }

    public static short[] split(ValueJoinFormat format, short value) {
        int intVal = (int) value;
        int[] digitList = format.encodeDigitList();
        short[] list = new short[digitList.length];
        for (int i = digitList.length; i > 0; i--) {
            int digits = digitList[i - 1];
            list[i - 1] = (short)(intVal & ((1 << digits) - 1));
            intVal >>>= digits;
        }
        return list;
    }

    private static short join(short origin, int digits, short value) {
        origin <<= digits;
        origin += value;
        return origin;
    }

    public class Builder {
        private short result = 0;
        private int usedDigits = 0;

        public short append(int digits, short value) throws ValueJoinException {
            usedDigits += digits;
            if (usedDigits > DIGITS_LIMIT) {
                throw new ValueJoinOutOfRangeException(DIGITS_LIMIT, usedDigits);
            }
            short limit = (short) (1 << digits - 1);
            if (value > limit) {
                throw new ValueJoinOutOfRangeException(limit, value);
            }
            result = join(result, digits, value);
            return result;
        }
    }
}
