package com.ken;

/**
 * Created by liuken on 2018/1/7.
 */
public class VariableValue {

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
