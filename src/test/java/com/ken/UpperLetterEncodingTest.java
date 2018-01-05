package com.ken;

import com.ken.config.StringEncodingConfig;
import com.ken.exception.InvalidCharacterException;
import com.ken.exception.OutOfRangeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuken on 2017/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StringEncodingConfig.class)
public class UpperLetterEncodingTest {

    @Autowired
    UpperLetterEncodeProvider upperLetterEncoding;

    @Test
    public void test_encodingToInterge() {
        EncodeProvider provider = new UpperLetterNumberMixEncodeProvider();
        try {
            int a = provider.encodeToInteger("AC");
            System.out.println(Integer.toBinaryString(a));

            String b = provider.decodeToString(a);
            System.out.println(b);

            a = provider.encodeToInteger("ACB");
            System.out.println(Integer.toBinaryString(a));

            b = provider.decodeToString(a);
            System.out.println(b);
        } catch(Exception e) {
            e.printStackTrace();
        }


        try {
            short a = provider.encodeToShort("ACB");
            System.out.println(Integer.toBinaryString(a));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof OutOfRangeException);
        }

        try {
            short a = provider.encodeToShort("ac");
            System.out.println(Integer.toBinaryString(a));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(e instanceof InvalidCharacterException);
        }
    }

    @Test
    public void test_mix() {
        EncodeProvider provider = new UpperLetterNumberMixEncodeProvider();

        try {
            int a = provider.encodeToInteger("A8AS9");
            System.out.println(Integer.toBinaryString(a));

            String b = provider.decodeToString(a);
            System.out.println(b);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
