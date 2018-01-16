package com.ken;

import com.ken.utils.LongExtensions;

import java.lang.reflect.Array;

/**
 * Created by liuken on 2018/1/7.
 * TODO: Serialization not supported!
 */
public final class VariableValue {

    private static final long END_VALUE = 0x8000000000000000L;

    private static final long[] EMPTY = new long[] {0L};

    private final long[] data;

    /**
     * Cache hashCode to speed up instance as key in hashMap
     */
    private final int hashCode;

    public VariableValue(long[] data) {
        this.data = data != null ? data : EMPTY;
        hashCode = calculateHashCode();
    }

    private int calculateHashCode() {
        int result = 0;
        for (long l : data) {
            int tmp = (int)(l ^ (l >>> 32));
            result = (((result << 5) + result) ^ tmp);
        }
        return result;
    }

    protected long[] getData() {
        return data;
    }

    public static String batchToHexString(VariableValue... values) {
        int count = 0;
        for (VariableValue value : values) {
            count += value != null ? value.data.length : EMPTY.length;
        }
        char[] buf = new char[count * LongExtensions.HEX_STRING_LENGTH];
        int offset = 0;
        for (VariableValue value : values) {
            long[] data = value != null ? value.data : EMPTY;

            int i = 0;
            for (; i < data.length - 1; i++) {
                offset = LongExtensions.fillInHexCharacters(buf, data[i], offset);
            }
            offset = LongExtensions.fillInHexCharacters(buf, data[i] + END_VALUE, offset);
        }
        return new String(buf);
    }

    public static VariableValue[] batchParse(String valStr) {
        int valLen = valStr.length();
        if (valLen % LongExtensions.HEX_STRING_LENGTH != 0) {
            throw new IllegalArgumentException(String.format("% is illegal!", valStr));
        }

        char[] charList = valStr.toCharArray();
        int valCount =  valLen / LongExtensions.HEX_STRING_LENGTH;
        long[] valList = new long[valCount];
        int offset = 0;
        int resultCount = 0;
        for (int i = 0; i < valCount; i++) {
            valList[i] = LongExtensions.parseLongFromHexCharacters(charList, offset);
            if (valList[i] < 0) {
                resultCount++;
            }
            offset += LongExtensions.HEX_STRING_LENGTH;
        }

        VariableValue[] resultList = new VariableValue[resultCount];
        int resultIndex = 0;
        int startIndex = 0;
        int len = 0;
        for (int i = 0; i < valList.length; i++) {
            len++;
            if (valList[i] < 0) {
                valList[i] -= END_VALUE;
                long[] data = new long[len];
                System.arraycopy(valList, startIndex, data, 0, len);
                resultList[resultIndex] = new VariableValue(data);

                resultIndex++;
                startIndex = i + 1;
                len = 0;
            }
        }
        return resultList;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof VariableValue)) {
            return false;
        }

        VariableValue v = (VariableValue) obj;
        if (v.data == null && data == null) {
            return true;
        }

        if (v.data == null || data == null) {
            return false;
        }

        if (v.data.length != data.length) {
            return false;
        }

        for (int i = 0; i < data.length; i++) {
            if (v.data[i] != data[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        char[] buf = new char[data.length * LongExtensions.HEX_STRING_LENGTH];
        int offset = 0;
        for (long val : data) {
            offset = LongExtensions.fillInHexCharacters(buf, val, offset);
        }
        return new String(buf);
    }
}
