package com.ken;

import org.junit.Test;

public class DefaultValueJoinFormatTest {

    @Test
    public void TestInitial() {
        DefaultValueJoinFormat format = new DefaultValueJoinFormat(23,34,51,21,4,13,24,56,2);

        for (int i = 0; i < 9; i++) {
            DefaultValueJoinFormat.ElementInfo info = format.getElementInfo(i);
            System.out.println(String.format("%d %d", info.getDigit(), info.getDataIndex()));
        }
    }
}
