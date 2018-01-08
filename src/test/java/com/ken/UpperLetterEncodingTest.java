package com.ken;

import com.ken.exception.InvalidCharacterException;
import com.ken.exception.OutOfRangeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuken on 2017/12/30.
 */
public class UpperLetterEncodingTest {

    @Test
    public void test_encodingToInterge() {
        EncodeProvider provider = new DefaultEncodeProvider();
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
        EncodeProvider provider = new DefaultEncodeProvider();

        try {
            int a = provider.encodeToInteger("A8AS9");
            System.out.println(Integer.toBinaryString(a));

            String b = provider.decodeToString(a);
            System.out.println(b);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_calculate() {

        List<Integer> list = new ArrayList<Integer>();
        list.add(7);
        list.add(7);
        list.add(7);
        list.add(7);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);

        List<BuckInfo> result = calculate(list, list.size() - 1, new ArrayList<BuckInfo>());
        System.out.println(result.size());
        for (BuckInfo buck : result) {
            for (Integer content : buck.getContents()) {
                System.out.print(content + " ");
            }
            System.out.println();
        }
    }
    private List<BuckInfo> calculate(List<Integer> originList, int index, List<BuckInfo> usedBucks) {

        if (index < 0) {
            return usedBucks;
        }

        Integer target = originList.get(index);
        index--;

        List<BuckInfo> usedStackNew = new ArrayList<BuckInfo>(usedBucks);
        BuckInfo newBuck = new BuckInfo();
        newBuck.append(target);
        usedStackNew.add(newBuck);
        List<BuckInfo> minUsedStack = calculate(originList, index, usedStackNew);;
        for (int i = 0; i < usedBucks.size(); i++) {
            BuckInfo existBuck = usedBucks.get(i);
            if (existBuck.getVolume() < target) {
                continue;
            }
            List<BuckInfo> tmpBucks = new ArrayList<BuckInfo>(usedBucks);
            newBuck = new BuckInfo(existBuck);
            newBuck.append(target);
            tmpBucks.set(i, newBuck);
            List<BuckInfo> tmpResult = calculate(originList, index, tmpBucks);
            if (tmpResult.size() < minUsedStack.size()) {
                minUsedStack = tmpResult;
            }
        }
        return minUsedStack;
    }

    private class BuckInfo {
        private static final int MAX_VOLUME = 10;

        private Integer volume;

        private List<Integer> contents;

        public BuckInfo(BuckInfo info) {
            volume = info.getVolume();
            contents = new ArrayList<Integer>(info.getContents());
        }

        public BuckInfo() {
            volume = MAX_VOLUME;
            contents = new ArrayList<Integer>(10);
        }

        public Integer getVolume() {
            return volume;
        }

        public List<Integer> getContents() {
            return contents;
        }

        public void append(Integer content) {
            contents.add(content);
            volume -= content;
        }
    }
}
