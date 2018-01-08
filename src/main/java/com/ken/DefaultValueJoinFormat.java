package com.ken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuken on 2018/1/8.
 */
public class DefaultValueJoinFormat implements ValueJoinFormat {

    private final int elementCount;
    private final int[] digitList;

    private DefaultValueJoinFormat.ElementInfo[] elementInfos;
    private int dataLength;

    public DefaultValueJoinFormat(int... digitList) {
        this.digitList = digitList;
        elementCount = digitList.length;
        initial();
    }

    public DefaultValueJoinFormat(ValueJoinFormat format) {
        this.digitList = format.encodeDigitList();
        elementCount = this.digitList.length;
        initial();
    }

    @Override
    public int getElementCount() {
        return elementCount;
    }

    @Override
    public int[] encodeDigitList() {
        return digitList;
    }

    public long[] createDatas() {
        return new long[dataLength];
    }

    public DefaultValueJoinFormat.ElementInfo getElementInfo(int index) {
        return elementInfos[index];
    }

    private void initial() {
        elementInfos = new DefaultValueJoinFormat.ElementInfo[elementCount];
        for (int i = 0; i < elementCount; i++) {
            elementInfos[i] = new DefaultValueJoinFormat.ElementInfo(digitList[i]);
        }

        List<BuckInfo> optimalBucks = calculateOptimal(digitList, digitList.length - 1, new ArrayList<BuckInfo>());
        dataLength = optimalBucks.size();
        for (int i = 0; i < optimalBucks.size(); i++) {
            BuckInfo buck = optimalBucks.get(i);
            for (Integer index : buck.getIndexCache()) {
                elementInfos[index].setDataIndex(i);
            }
        }
    }

    private List<BuckInfo> calculateOptimal(int[] originList, int index, List<BuckInfo> usedBucks) {
        if (index < 0) {
            return usedBucks;
        }

        Integer curIndex = index;
        Integer target = originList[curIndex];
        index--;

        List<BuckInfo> usedBucksNew = new ArrayList<BuckInfo>(usedBucks);
        BuckInfo newBuck = new BuckInfo();
        newBuck.append(target, curIndex);
        usedBucksNew.add(newBuck);
        List<BuckInfo> minUsedBucks = calculateOptimal(originList, index, usedBucksNew);;
        for (int i = 0; i < usedBucks.size(); i++) {
            BuckInfo existBuck = usedBucks.get(i);
            if (!existBuck.canAppend(target)) {
                continue;
            }
            List<BuckInfo> tmpBucks = new ArrayList<BuckInfo>(usedBucks);
            newBuck = new BuckInfo(existBuck);
            newBuck.append(target, curIndex);
            tmpBucks.set(i, newBuck);
            List<BuckInfo> tmpResult = calculateOptimal(originList, index, tmpBucks);
            if (tmpResult.size() < minUsedBucks.size()) {
                minUsedBucks = tmpResult;
            }
        }
        return minUsedBucks;
    }

    public class ElementInfo {
        private final int digit;
        private final short valueLimitShort;
        private final int valueLimitInteger;
        private final long valueLimitLong;

        private int dataIndex;

        public ElementInfo(int digit) {
            this.digit = digit;
            valueLimitShort = getShortLimit(digit);
            valueLimitInteger = getIntegerLimit(digit);
            valueLimitLong = getLongLimit(digit);
        }

        public int getDataIndex() {
            return dataIndex;
        }

        public void setDataIndex(int dataIndex) {
            this.dataIndex = dataIndex;
        }

        public int getDigit() {
            return digit;
        }

        public short getValueLimitShort() {
            return valueLimitShort;
        }

        public int getValueLimitInteger() {
            return valueLimitInteger;
        }

        public long getValueLimitLong() {
            return valueLimitLong;
        }

        private short getShortLimit(int digit) {
            switch (digit) {
                case 1:
                    return ConstValue.SHORT_DIGIT_1;
                case 2:
                    return ConstValue.SHORT_DIGIT_2;
                case 3:
                    return ConstValue.SHORT_DIGIT_3;
                case 4:
                    return ConstValue.SHORT_DIGIT_4;
                case 5:
                    return ConstValue.SHORT_DIGIT_5;
                case 6:
                    return ConstValue.SHORT_DIGIT_6;
                case 7:
                    return ConstValue.SHORT_DIGIT_7;
                case 8:
                    return ConstValue.SHORT_DIGIT_8;
                case 9:
                    return ConstValue.SHORT_DIGIT_9;
                case 10:
                    return ConstValue.SHORT_DIGIT_10;
                case 11:
                    return ConstValue.SHORT_DIGIT_11;
                case 12:
                    return ConstValue.SHORT_DIGIT_12;
                case 13:
                    return ConstValue.SHORT_DIGIT_13;
                case 14:
                    return ConstValue.SHORT_DIGIT_14;
                case 15:
                    return ConstValue.SHORT_DIGIT_15;
                case 16:
                    return ConstValue.SHORT_DIGIT_15;
                case 17:
                    return ConstValue.SHORT_DIGIT_15;
                case 18:
                    return ConstValue.SHORT_DIGIT_15;
                case 19:
                    return ConstValue.SHORT_DIGIT_15;
                case 20:
                    return ConstValue.SHORT_DIGIT_15;
                case 21:
                    return ConstValue.SHORT_DIGIT_15;
                case 22:
                    return ConstValue.SHORT_DIGIT_15;
                case 23:
                    return ConstValue.SHORT_DIGIT_15;
                case 24:
                    return ConstValue.SHORT_DIGIT_15;
                case 25:
                    return ConstValue.SHORT_DIGIT_15;
                case 26:
                    return ConstValue.SHORT_DIGIT_15;
                case 27:
                    return ConstValue.SHORT_DIGIT_15;
                case 28:
                    return ConstValue.SHORT_DIGIT_15;
                case 29:
                    return ConstValue.SHORT_DIGIT_15;
                case 30:
                    return ConstValue.SHORT_DIGIT_15;
                case 31:
                    return ConstValue.SHORT_DIGIT_15;
                case 32:
                    return ConstValue.SHORT_DIGIT_15;
                case 33:
                    return ConstValue.SHORT_DIGIT_15;
                case 34:
                    return ConstValue.SHORT_DIGIT_15;
                case 35:
                    return ConstValue.SHORT_DIGIT_15;
                case 36:
                    return ConstValue.SHORT_DIGIT_15;
                case 37:
                    return ConstValue.SHORT_DIGIT_15;
                case 38:
                    return ConstValue.SHORT_DIGIT_15;
                case 39:
                    return ConstValue.SHORT_DIGIT_15;
                case 40:
                    return ConstValue.SHORT_DIGIT_15;
                case 41:
                    return ConstValue.SHORT_DIGIT_15;
                case 42:
                    return ConstValue.SHORT_DIGIT_15;
                case 43:
                    return ConstValue.SHORT_DIGIT_15;
                case 44:
                    return ConstValue.SHORT_DIGIT_15;
                case 45:
                    return ConstValue.SHORT_DIGIT_15;
                case 46:
                    return ConstValue.SHORT_DIGIT_15;
                case 47:
                    return ConstValue.SHORT_DIGIT_15;
                case 48:
                    return ConstValue.SHORT_DIGIT_15;
                case 49:
                    return ConstValue.SHORT_DIGIT_15;
                case 50:
                    return ConstValue.SHORT_DIGIT_15;
                case 51:
                    return ConstValue.SHORT_DIGIT_15;
                case 52:
                    return ConstValue.SHORT_DIGIT_15;
                case 53:
                    return ConstValue.SHORT_DIGIT_15;
                case 54:
                    return ConstValue.SHORT_DIGIT_15;
                case 55:
                    return ConstValue.SHORT_DIGIT_15;
                case 56:
                    return ConstValue.SHORT_DIGIT_15;
                case 57:
                    return ConstValue.SHORT_DIGIT_15;
                case 58:
                    return ConstValue.SHORT_DIGIT_15;
                case 59:
                    return ConstValue.SHORT_DIGIT_15;
                case 60:
                    return ConstValue.SHORT_DIGIT_15;
                case 61:
                    return ConstValue.SHORT_DIGIT_15;
                case 62:
                    return ConstValue.SHORT_DIGIT_15;
                case 63:
                    return ConstValue.SHORT_DIGIT_15;
                default:
                    throw new IllegalArgumentException(String.format("%d digits exceed limit!", digit));
            }
        }

        private int getIntegerLimit(int digit) {
            switch (digit) {
                case 1:
                    return ConstValue.INTEGER_DIGIT_1;
                case 2:
                    return ConstValue.INTEGER_DIGIT_2;
                case 3:
                    return ConstValue.INTEGER_DIGIT_3;
                case 4:
                    return ConstValue.INTEGER_DIGIT_4;
                case 5:
                    return ConstValue.INTEGER_DIGIT_5;
                case 6:
                    return ConstValue.INTEGER_DIGIT_6;
                case 7:
                    return ConstValue.INTEGER_DIGIT_7;
                case 8:
                    return ConstValue.INTEGER_DIGIT_8;
                case 9:
                    return ConstValue.INTEGER_DIGIT_9;
                case 10:
                    return ConstValue.INTEGER_DIGIT_10;
                case 11:
                    return ConstValue.INTEGER_DIGIT_11;
                case 12:
                    return ConstValue.INTEGER_DIGIT_12;
                case 13:
                    return ConstValue.INTEGER_DIGIT_13;
                case 14:
                    return ConstValue.INTEGER_DIGIT_14;
                case 15:
                    return ConstValue.INTEGER_DIGIT_15;
                case 16:
                    return ConstValue.INTEGER_DIGIT_16;
                case 17:
                    return ConstValue.INTEGER_DIGIT_17;
                case 18:
                    return ConstValue.INTEGER_DIGIT_18;
                case 19:
                    return ConstValue.INTEGER_DIGIT_19;
                case 20:
                    return ConstValue.INTEGER_DIGIT_20;
                case 21:
                    return ConstValue.INTEGER_DIGIT_21;
                case 22:
                    return ConstValue.INTEGER_DIGIT_22;
                case 23:
                    return ConstValue.INTEGER_DIGIT_23;
                case 24:
                    return ConstValue.INTEGER_DIGIT_24;
                case 25:
                    return ConstValue.INTEGER_DIGIT_25;
                case 26:
                    return ConstValue.INTEGER_DIGIT_26;
                case 27:
                    return ConstValue.INTEGER_DIGIT_27;
                case 28:
                    return ConstValue.INTEGER_DIGIT_28;
                case 29:
                    return ConstValue.INTEGER_DIGIT_29;
                case 30:
                    return ConstValue.INTEGER_DIGIT_30;
                case 31:
                    return ConstValue.INTEGER_DIGIT_31;
                case 32:
                    return ConstValue.INTEGER_DIGIT_31;
                case 33:
                    return ConstValue.INTEGER_DIGIT_31;
                case 34:
                    return ConstValue.INTEGER_DIGIT_31;
                case 35:
                    return ConstValue.INTEGER_DIGIT_31;
                case 36:
                    return ConstValue.INTEGER_DIGIT_31;
                case 37:
                    return ConstValue.INTEGER_DIGIT_31;
                case 38:
                    return ConstValue.INTEGER_DIGIT_31;
                case 39:
                    return ConstValue.INTEGER_DIGIT_31;
                case 40:
                    return ConstValue.INTEGER_DIGIT_31;
                case 41:
                    return ConstValue.INTEGER_DIGIT_31;
                case 42:
                    return ConstValue.INTEGER_DIGIT_31;
                case 43:
                    return ConstValue.INTEGER_DIGIT_31;
                case 44:
                    return ConstValue.INTEGER_DIGIT_31;
                case 45:
                    return ConstValue.INTEGER_DIGIT_31;
                case 46:
                    return ConstValue.INTEGER_DIGIT_31;
                case 47:
                    return ConstValue.INTEGER_DIGIT_31;
                case 48:
                    return ConstValue.INTEGER_DIGIT_31;
                case 49:
                    return ConstValue.INTEGER_DIGIT_31;
                case 50:
                    return ConstValue.INTEGER_DIGIT_31;
                case 51:
                    return ConstValue.INTEGER_DIGIT_31;
                case 52:
                    return ConstValue.INTEGER_DIGIT_31;
                case 53:
                    return ConstValue.INTEGER_DIGIT_31;
                case 54:
                    return ConstValue.INTEGER_DIGIT_31;
                case 55:
                    return ConstValue.INTEGER_DIGIT_31;
                case 56:
                    return ConstValue.INTEGER_DIGIT_31;
                case 57:
                    return ConstValue.INTEGER_DIGIT_31;
                case 58:
                    return ConstValue.INTEGER_DIGIT_31;
                case 59:
                    return ConstValue.INTEGER_DIGIT_31;
                case 60:
                    return ConstValue.INTEGER_DIGIT_31;
                case 61:
                    return ConstValue.INTEGER_DIGIT_31;
                case 62:
                    return ConstValue.INTEGER_DIGIT_31;
                case 63:
                    return ConstValue.INTEGER_DIGIT_31;
                default:
                    throw new IllegalArgumentException(String.format("%d digits exceed limit!", digit));
            }
        }

        private long getLongLimit(int digit) {
            switch (digit) {
                case 1:
                    return ConstValue.LONG_DIGIT_1;
                case 2:
                    return ConstValue.LONG_DIGIT_2;
                case 3:
                    return ConstValue.LONG_DIGIT_3;
                case 4:
                    return ConstValue.LONG_DIGIT_4;
                case 5:
                    return ConstValue.LONG_DIGIT_5;
                case 6:
                    return ConstValue.LONG_DIGIT_6;
                case 7:
                    return ConstValue.LONG_DIGIT_7;
                case 8:
                    return ConstValue.LONG_DIGIT_8;
                case 9:
                    return ConstValue.LONG_DIGIT_9;
                case 10:
                    return ConstValue.LONG_DIGIT_10;
                case 11:
                    return ConstValue.LONG_DIGIT_11;
                case 12:
                    return ConstValue.LONG_DIGIT_12;
                case 13:
                    return ConstValue.LONG_DIGIT_13;
                case 14:
                    return ConstValue.LONG_DIGIT_14;
                case 15:
                    return ConstValue.LONG_DIGIT_15;
                case 16:
                    return ConstValue.LONG_DIGIT_16;
                case 17:
                    return ConstValue.LONG_DIGIT_17;
                case 18:
                    return ConstValue.LONG_DIGIT_18;
                case 19:
                    return ConstValue.LONG_DIGIT_19;
                case 20:
                    return ConstValue.LONG_DIGIT_20;
                case 21:
                    return ConstValue.LONG_DIGIT_21;
                case 22:
                    return ConstValue.LONG_DIGIT_22;
                case 23:
                    return ConstValue.LONG_DIGIT_23;
                case 24:
                    return ConstValue.LONG_DIGIT_24;
                case 25:
                    return ConstValue.LONG_DIGIT_25;
                case 26:
                    return ConstValue.LONG_DIGIT_26;
                case 27:
                    return ConstValue.LONG_DIGIT_27;
                case 28:
                    return ConstValue.LONG_DIGIT_28;
                case 29:
                    return ConstValue.LONG_DIGIT_29;
                case 30:
                    return ConstValue.LONG_DIGIT_30;
                case 31:
                    return ConstValue.LONG_DIGIT_31;
                case 32:
                    return ConstValue.LONG_DIGIT_32;
                case 33:
                    return ConstValue.LONG_DIGIT_33;
                case 34:
                    return ConstValue.LONG_DIGIT_34;
                case 35:
                    return ConstValue.LONG_DIGIT_35;
                case 36:
                    return ConstValue.LONG_DIGIT_36;
                case 37:
                    return ConstValue.LONG_DIGIT_37;
                case 38:
                    return ConstValue.LONG_DIGIT_38;
                case 39:
                    return ConstValue.LONG_DIGIT_39;
                case 40:
                    return ConstValue.LONG_DIGIT_40;
                case 41:
                    return ConstValue.LONG_DIGIT_41;
                case 42:
                    return ConstValue.LONG_DIGIT_42;
                case 43:
                    return ConstValue.LONG_DIGIT_43;
                case 44:
                    return ConstValue.LONG_DIGIT_44;
                case 45:
                    return ConstValue.LONG_DIGIT_45;
                case 46:
                    return ConstValue.LONG_DIGIT_46;
                case 47:
                    return ConstValue.LONG_DIGIT_47;
                case 48:
                    return ConstValue.LONG_DIGIT_48;
                case 49:
                    return ConstValue.LONG_DIGIT_49;
                case 50:
                    return ConstValue.LONG_DIGIT_50;
                case 51:
                    return ConstValue.LONG_DIGIT_51;
                case 52:
                    return ConstValue.LONG_DIGIT_52;
                case 53:
                    return ConstValue.LONG_DIGIT_53;
                case 54:
                    return ConstValue.LONG_DIGIT_54;
                case 55:
                    return ConstValue.LONG_DIGIT_55;
                case 56:
                    return ConstValue.LONG_DIGIT_56;
                case 57:
                    return ConstValue.LONG_DIGIT_57;
                case 58:
                    return ConstValue.LONG_DIGIT_58;
                case 59:
                    return ConstValue.LONG_DIGIT_59;
                case 60:
                    return ConstValue.LONG_DIGIT_60;
                case 61:
                    return ConstValue.LONG_DIGIT_61;
                case 62:
                    return ConstValue.LONG_DIGIT_62;
                case 63:
                    return ConstValue.LONG_DIGIT_63;
                default:
                    throw new IllegalArgumentException(String.format("%d digits exceed limit!", digit));
            }
        }
    }

    private class ConstValue {
        public final static short SHORT_DIGIT_1 = 1;
        public final static short SHORT_DIGIT_2 = 3;
        public final static short SHORT_DIGIT_3 = 7;
        public final static short SHORT_DIGIT_4 = 15;
        public final static short SHORT_DIGIT_5 = 31;
        public final static short SHORT_DIGIT_6 = 63;
        public final static short SHORT_DIGIT_7 = 127;
        public final static short SHORT_DIGIT_8 = 255;
        public final static short SHORT_DIGIT_9 = 511;
        public final static short SHORT_DIGIT_10 = 1023;
        public final static short SHORT_DIGIT_11 = 2047;
        public final static short SHORT_DIGIT_12 = 4095;
        public final static short SHORT_DIGIT_13 = 8191;
        public final static short SHORT_DIGIT_14 = 16383;
        public final static short SHORT_DIGIT_15 = 32767;

        public final static int INTEGER_DIGIT_1 = 1;
        public final static int INTEGER_DIGIT_2 = 3;
        public final static int INTEGER_DIGIT_3 = 7;
        public final static int INTEGER_DIGIT_4 = 15;
        public final static int INTEGER_DIGIT_5 = 31;
        public final static int INTEGER_DIGIT_6 = 63;
        public final static int INTEGER_DIGIT_7 = 127;
        public final static int INTEGER_DIGIT_8 = 255;
        public final static int INTEGER_DIGIT_9 = 511;
        public final static int INTEGER_DIGIT_10 = 1023;
        public final static int INTEGER_DIGIT_11 = 2047;
        public final static int INTEGER_DIGIT_12 = 4095;
        public final static int INTEGER_DIGIT_13 = 8191;
        public final static int INTEGER_DIGIT_14 = 16383;
        public final static int INTEGER_DIGIT_15 = 32767;
        public final static int INTEGER_DIGIT_16 = 65535;
        public final static int INTEGER_DIGIT_17 = 131071;
        public final static int INTEGER_DIGIT_18 = 262143;
        public final static int INTEGER_DIGIT_19 = 524287;
        public final static int INTEGER_DIGIT_20 = 1048575;
        public final static int INTEGER_DIGIT_21 = 2097151;
        public final static int INTEGER_DIGIT_22 = 4194303;
        public final static int INTEGER_DIGIT_23 = 8388607;
        public final static int INTEGER_DIGIT_24 = 16777215;
        public final static int INTEGER_DIGIT_25 = 33554431;
        public final static int INTEGER_DIGIT_26 = 67108863;
        public final static int INTEGER_DIGIT_27 = 134217727;
        public final static int INTEGER_DIGIT_28 = 268435455;
        public final static int INTEGER_DIGIT_29 = 536870911;
        public final static int INTEGER_DIGIT_30 = 1073741823;
        public final static int INTEGER_DIGIT_31 = 2147483647;

        public final static long LONG_DIGIT_1 = 1L;
        public final static long LONG_DIGIT_2 = 3L;
        public final static long LONG_DIGIT_3 = 7L;
        public final static long LONG_DIGIT_4 = 15L;
        public final static long LONG_DIGIT_5 = 31L;
        public final static long LONG_DIGIT_6 = 63L;
        public final static long LONG_DIGIT_7 = 127L;
        public final static long LONG_DIGIT_8 = 255L;
        public final static long LONG_DIGIT_9 = 511L;
        public final static long LONG_DIGIT_10 = 1023L;
        public final static long LONG_DIGIT_11 = 2047L;
        public final static long LONG_DIGIT_12 = 4095L;
        public final static long LONG_DIGIT_13 = 8191L;
        public final static long LONG_DIGIT_14 = 16383L;
        public final static long LONG_DIGIT_15 = 32767L;
        public final static long LONG_DIGIT_16 = 65535L;
        public final static long LONG_DIGIT_17 = 131071L;
        public final static long LONG_DIGIT_18 = 262143L;
        public final static long LONG_DIGIT_19 = 524287L;
        public final static long LONG_DIGIT_20 = 1048575L;
        public final static long LONG_DIGIT_21 = 2097151L;
        public final static long LONG_DIGIT_22 = 4194303L;
        public final static long LONG_DIGIT_23 = 8388607L;
        public final static long LONG_DIGIT_24 = 16777215L;
        public final static long LONG_DIGIT_25 = 33554431L;
        public final static long LONG_DIGIT_26 = 67108863L;
        public final static long LONG_DIGIT_27 = 134217727L;
        public final static long LONG_DIGIT_28 = 268435455L;
        public final static long LONG_DIGIT_29 = 536870911L;
        public final static long LONG_DIGIT_30 = 1073741823L;
        public final static long LONG_DIGIT_31 = 2147483647L;
        public final static long LONG_DIGIT_32 = 2147483647L;
        public final static long LONG_DIGIT_33 = 4294967295L;
        public final static long LONG_DIGIT_34 = 8589934591L;
        public final static long LONG_DIGIT_35 = 17179869183L;
        public final static long LONG_DIGIT_36 = 34359738367L;
        public final static long LONG_DIGIT_37 = 68719476735L;
        public final static long LONG_DIGIT_38 = 137438953471L;
        public final static long LONG_DIGIT_39 = 274877906943L;
        public final static long LONG_DIGIT_40 = 549755813887L;
        public final static long LONG_DIGIT_41 = 1099511627775L;
        public final static long LONG_DIGIT_42 = 2199023255551L;
        public final static long LONG_DIGIT_43 = 4398046511103L;
        public final static long LONG_DIGIT_44 = 8796093022207L;
        public final static long LONG_DIGIT_45 = 17592186044415L;
        public final static long LONG_DIGIT_46 = 35184372088831L;
        public final static long LONG_DIGIT_47 = 70368744177663L;
        public final static long LONG_DIGIT_48 = 140737488355327L;
        public final static long LONG_DIGIT_49 = 281474976710655L;
        public final static long LONG_DIGIT_50 = 562949953421311L;
        public final static long LONG_DIGIT_51 = 1125899906842623L;
        public final static long LONG_DIGIT_52 = 2251799813685247L;
        public final static long LONG_DIGIT_53 = 4503599627370495L;
        public final static long LONG_DIGIT_54 = 9007199254740991L;
        public final static long LONG_DIGIT_55 = 18014398509481983L;
        public final static long LONG_DIGIT_56 = 36028797018963967L;
        public final static long LONG_DIGIT_57 = 72057594037927935L;
        public final static long LONG_DIGIT_58 = 144115188075855871L;
        public final static long LONG_DIGIT_59 = 288230376151711743L;
        public final static long LONG_DIGIT_60 = 576460752303423487L;
        public final static long LONG_DIGIT_61 = 1152921504606846975L;
        public final static long LONG_DIGIT_62 = 2305843009213693951L;
        public final static long LONG_DIGIT_63 = 4611686018427387903L;
        public final static long LONG_DIGIT_64 = 9223372036854775807L;
    }

    private class BuckInfo {
        private static final int MAX_VOLUME = Long.SIZE - 1;

        private int volume;

        private List<Integer> indexCache;

        public BuckInfo(BuckInfo info) {
            volume = info.getVolume();
            indexCache = new ArrayList<Integer>(info.getIndexCache());
        }

        public BuckInfo() {
            volume = MAX_VOLUME;
            indexCache = new ArrayList<Integer>(10);
        }

        public int getVolume() {
            return volume;
        }

        public List<Integer> getIndexCache() {
            return indexCache;
        }

        public void append(int content, Integer index) {
            indexCache.add(index);
            volume -= content;
        }

        public boolean canAppend(int content) {
            return volume >= content;
        }
    }
}
