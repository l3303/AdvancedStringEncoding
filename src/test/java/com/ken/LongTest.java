package com.ken;

import org.junit.Test;

public class LongTest {

    @Test
    public void test() {
        long a = 0x7000000000000000L;
        System.out.println(Long.toBinaryString(a << 1));
        System.out.println((a << 1) < 0);

        long b = a << 1;
        long c = b >>> 1;
        System.out.println(Long.toBinaryString(c));
        System.out.println(c == a);
    }
}
