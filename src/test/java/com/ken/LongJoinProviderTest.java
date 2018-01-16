package com.ken;

import com.ken.simple.LongJoinProvider;
import com.ken.simple.SimpleValueJoinFormat;
import org.junit.Assert;
import org.junit.Test;

public class LongJoinProviderTest {

    @Test
    public void test() {
        SimpleValueJoinFormat svf = new SimpleValueJoinFormat(CommonStringValueDigits.AIRLINE, CommonStringValueDigits.FLIGHTNO);

        DefaultEncodeProvider provider = new DefaultEncodeProvider();
        try {
            long[] input = new long[] {provider.encodeToLong("MU"), 501};
            long a = LongJoinProvider.join(svf, input);
            System.out.println(Long.toBinaryString(a));

            long[] output = LongJoinProvider.split(svf, a);
            for (int i = 0; i < output.length; i++) {
                Assert.assertEquals(output[i], input[i]);
            }

        } catch (Exception ex) {

        }
    }

    @Test
    public void test_builder() {
        long a = 0;

        LongJoinProvider.Builder builder = new LongJoinProvider.Builder();

        try {
            long b = builder.append(32, 1L);
            System.out.println(Long.toBinaryString(b));

            long c = builder.append(32, 1L);
            System.out.println(Long.toBinaryString(c));

            long d = builder.append(1, 1L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
