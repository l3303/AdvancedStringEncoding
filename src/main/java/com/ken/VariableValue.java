package com.ken;

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

        StringBuilder sb = new StringBuilder((Long.SIZE - 1) * data.length);
        for (int i = data.length; i > 0; i--) {
            sb.append(data[i - 1]);
        }
        return sb.toString();
    }
}
