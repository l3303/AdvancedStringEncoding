package com.ken;

import com.ken.exception.ValueJoinOutOfRangeException;
import org.junit.Assert;
import org.junit.Test;

public class DefaultValueJoinProviderTest {

    @Test
    public void test() {
        DefaultValueJoinFormat format = new DefaultValueJoinFormat(CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.YEAR, CommonStringValueDigits.MONTH, CommonStringValueDigits.DATE, CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.YEAR, CommonStringValueDigits.MONTH, CommonStringValueDigits.DATE);

        DefaultValueJoinProvider provider = new DefaultValueJoinProvider();

        DefaultEncodeProvider encodeProvider = new DefaultEncodeProvider();
        try {

            Object result = provider.join(format, encodeProvider.encodeToInteger("SHA"), encodeProvider.encodeToInteger("NYC"), 2018, 4, 25, encodeProvider.encodeToInteger("NYC"), encodeProvider.encodeToInteger("SHA"), 2018, 4, 28);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        format = new DefaultValueJoinFormat(CommonStringValueDigits.AIRLINE, CommonStringValueDigits.CITY_CODE);

        try {

            Object result = provider.join(format, encodeProvider.encodeToShort("MU"), (short) encodeProvider.encodeToInteger("SHA"));
            System.out.println(result);

            result = provider.join(format, Short.MAX_VALUE, encodeProvider.encodeToInteger("SHA"));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.assertTrue(e instanceof ValueJoinOutOfRangeException);
        }
    }

    @Test
    public void test_routeSearchToken() {
        DefaultValueJoinFormat format = new DefaultValueJoinFormat(
                CommonStringValueDigits.ENGINETYPE,
                CommonStringValueDigits.AIRLINE,
                CommonStringValueDigits.FLIGHTNO,
                CommonStringValueDigits.AIRLINE,
                CommonStringValueDigits.FLIGHTNO,
                CommonStringValueDigits.AIRLINE,
                CommonStringValueDigits.FLIGHTNO,
                CommonStringValueDigits.AIRLINE,
                CommonStringValueDigits.FLIGHTNO,
                CommonStringValueDigits.SEATGRADE,
                6,
                CommonStringValueDigits.SEATGRADE,
                6,
                CommonStringValueDigits.SEATGRADE,
                6,
                CommonStringValueDigits.SEATGRADE,
                6,
                16,
                12,
                CommonStringValueDigits.AGENCY_ID,
                CommonStringValueDigits.AIRLINE,
                CommonStringValueDigits.ELIGIBILITY_CODE
        );

        DefaultValueJoinProvider provider = new DefaultValueJoinProvider();

        DefaultEncodeProvider encodeProvider = new DefaultEncodeProvider();
        try {

            int[] array = new int[]{
                    16,
                    encodeProvider.encodeToInteger("MU"),
                    501,
                    encodeProvider.encodeToInteger("FM"),
                    502,
                    encodeProvider.encodeToInteger("CX"),
                    503,
                    encodeProvider.encodeToInteger("KE"),
                    504,
                    1,
                    encodeProvider.encodeToInteger("Z"),
                    1,
                    encodeProvider.encodeToInteger("Z"),
                    1,
                    encodeProvider.encodeToInteger("Z"),
                    1,
                    encodeProvider.encodeToInteger("Z"),
                    1032,
                    301,
                    137,
                    encodeProvider.encodeToInteger("MU"),
                    encodeProvider.encodeToInteger("NOR")};
            Object result = provider.join(format, array);

            long[] origins = provider.split(format, result);

            for (int i = 0; i < origins.length; i++) {
                Assert.assertEquals(origins[i], (long) array[i]);
            }

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
