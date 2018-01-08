package com.ken;

import com.ken.exception.ValueJoinOutOfRangeException;
import org.junit.Assert;
import org.junit.Test;

public class DefaultValueJoinProviderTest {

    @Test
    public void test() {
        DefaultValueJoinFormat format = new DefaultValueJoinFormat(CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.YEAR, CommonStringValueDigits.MONTH, CommonStringValueDigits.DATE, CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.CITY_CODE, CommonStringValueDigits.YEAR, CommonStringValueDigits.MONTH, CommonStringValueDigits.DATE);

        DefaultValueJoinProvider provider = new DefaultValueJoinProvider();

        UpperLetterNumberMixEncodeProvider encodeProvider = new UpperLetterNumberMixEncodeProvider();
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
}
