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

    @Override
    public int hashCode() {
        return hashCode;
    }
}
