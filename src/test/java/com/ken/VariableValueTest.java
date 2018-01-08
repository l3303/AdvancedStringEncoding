package com.ken;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class VariableValueTest {

    @Test
    public void test() {
        VariableValue test = new VariableValue(new long[]{348272719212L, 842619284L});

        System.out.println(test);
        System.out.println(test.hashCode());

        HashMap<VariableValue, Integer> map = new HashMap<VariableValue, Integer>();
        map.put(test, 3);

        Assert.assertEquals("842619284348272719212", test.toString());
        Assert.assertEquals(3, map.get(test).intValue());

        test = new VariableValue(null);

        System.out.println(test);
        System.out.println(test.hashCode());
    }
}
