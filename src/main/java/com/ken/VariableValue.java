package com.ken;

import com.ken.utils.LongExtensions;

/**
 * Created by liuken on 2018/1/7.
 * TODO: Serialization not supported!
 */
public final class VariableValue {

    private final long[] data;

    /**
     * Cache hashCode to speed up instance as key in hashMap
     */
    private final int hashCode;

    public VariableValue(long[] data) {
        this.data = data;
        if (this.data != null) {
            hashCode = calculateHashCode();
        } else {
            hashCode = super.hashCode();
        }
    }

    private int calculateHashCode() {
        int result = 0;
        for (long l : data) {
            int tmp = (int)(l ^ (l >>> 32));
            result = (((result << 5) + result) ^ tmp);
        }
        return result;
    }

    public static String batchToHexString(VariableValue... values) {
        int count = 0;
        for (VariableValue value : values) {
            count += value.data.length;
        }
        char[] buf = new char[count * LongExtensions.HEX_STRING_LENGTH];
        int offset = 0;
        for (VariableValue value : values) {
            for (long val : value.data) {
                offset = LongExtensions.fillInHexCharacters(buf, val, offset);
            }
        }
        return new String(buf);
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
        if (data == null) {
            return "";
        }

        char[] buf = new char[LongExtensions.HEX_STRING_LENGTH];
        int offset = 0;
        for (long val : data) {
            offset = LongExtensions.fillInHexCharacters(buf, val, offset);
        }
        return new String(buf);
    }
}
