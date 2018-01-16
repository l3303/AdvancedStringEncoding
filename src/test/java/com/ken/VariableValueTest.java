package com.ken;

import com.ken.utils.LongExtensions;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class VariableValueTest {

    @Test
    public void test() {
        System.out.println(Long.toBinaryString(0x8000000000000000L >>> 3));
        System.out.println(Long.toBinaryString(0x8000000000000000L >> 3));


        System.out.println(16 << 12);

//        VariableValue test = new VariableValue(new long[]{348272719212L, 842619284L});
//
//        System.out.println(test);
//        System.out.println(test.hashCode());
//
//        HashMap<VariableValue, Integer> map = new HashMap<VariableValue, Integer>();
//        map.put(test, 3);
//
//        Assert.assertEquals("5116abf56c32395994", test.toString());
//        Assert.assertEquals(3, map.get(test).intValue());
//
//        test = new VariableValue(null);
//
//        System.out.println(test);
//        System.out.println(test.hashCode());

        long a = 0x8000000000000000L + 1L;
//        System.out.println(Long.toBinaryString(a));
//        a -= 0x8000000000000000L;
//        System.out.println(Long.toBinaryString(a));



        System.out.println(Long.toBinaryString(LongExtensions.parseLongFromHexCharacters("8000000000000001".toCharArray(), 0)));
//        System.out.println(0x8000000000000000L + 1L);
//        System.out.println(Long.toBinaryString(0x8000000000000000L));
//        System.out.println(Long.toHexString(0x8000000000000000L));

//        VariableValue test = new VariableValue(null);
//        System.out.println(test);

//        VariableValue test = new VariableValue(new long[]{1L,1L});
//        System.out.println(test);
    }

    @Test
    public void test1() {
        VariableValue v1 = new VariableValue(new long[]{3L, 4L});
        VariableValue v2 = new VariableValue(new long[]{5L, 6L});

        String a = VariableValue.batchToHexString(v1, v2);
        VariableValue[] vlist = VariableValue.batchParse(a);

        System.out.println(a);

        Assert.assertEquals(2, vlist.length);
    }
}
