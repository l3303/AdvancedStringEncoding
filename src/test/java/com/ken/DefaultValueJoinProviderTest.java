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
                Assert.assertEquals((long) array[i], origins[i]);
            }

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_routeSearchToken2() {
        DefaultValueJoinFormat format1 = new DefaultValueJoinFormat(
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
                6
        );

        DefaultValueJoinFormat format2 = new DefaultValueJoinFormat(
                CommonStringValueDigits.ENGINETYPE,
                16,
                12,
                CommonStringValueDigits.AGENCY_ID,
                CommonStringValueDigits.AIRLINE,
                CommonStringValueDigits.ELIGIBILITY_CODE
        );

        DefaultValueJoinProvider provider = new DefaultValueJoinProvider();

        DefaultEncodeProvider encodeProvider = new DefaultEncodeProvider();
        try {

            int[] array1 = new int[]{
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
                    encodeProvider.encodeToInteger("Z")};

            int[] array2 = new int[]{
                    16,
                    1032,
                    301,
                    137,
                    encodeProvider.encodeToInteger("MU"),
                    encodeProvider.encodeToInteger("NOR")};

            Object result1 = provider.join(format1, array1);
            Object result2 = provider.join(format2, array2);

            String a = VariableValue.batchToHexString((VariableValue)result1, (VariableValue)result2);
            System.out.println(a);

            VariableValue[] results = VariableValue.batchParse(a);

            long[] origins1 = provider.split(format1, results[0]);
            long[] origins2 = provider.split(format2, results[1]);

            for (int i = 0; i < origins1.length; i++) {
                Assert.assertEquals((long) array1[i], origins1[i]);
            }

            for (int i = 0; i < origins2.length; i++) {
                Assert.assertEquals((long) array2[i], origins2[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
