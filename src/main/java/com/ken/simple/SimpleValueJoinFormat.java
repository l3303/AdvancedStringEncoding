package com.ken.simple;

import com.ken.ValueJoinFormat;

public class SimpleValueJoinFormat implements ValueJoinFormat {

    private final static int[] EMPTY = new int[0];

    private final int[] digitList;

    public SimpleValueJoinFormat(int... digitList) {
        this.digitList = digitList != null ? digitList : EMPTY;
    }

    @Override
    public int getElementCount() {
        return digitList.length;
    }

    @Override
    public int[] encodeDigitList() {
        return digitList;
    }
}
