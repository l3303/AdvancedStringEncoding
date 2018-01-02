package com.ken;

import com.ken.config.StringEncodingConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuken on 2017/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StringEncodingConfig.class)
public class UpperLetterEncodingTest {

    @Autowired
    UpperLetterEncoding upperLetterEncoding;

    @Test
    public void test_encodingToInterge() {
        try {
            int a = upperLetterEncoding.encodingToInterge("AA");
            System.out.println(Integer.toBinaryString(a));

            String b = upperLetterEncoding.decodingToString(a);
            System.out.println(b);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
