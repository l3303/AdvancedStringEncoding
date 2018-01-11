package com.ken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuken on 2018/1/8.
 */
public class DefaultValueJoinFormat implements ValueJoinFormat {

    private static final int MAX_VOLUME = Long.SIZE - 1;

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
        int startIndex = 0;
        int curVolume = MAX_VOLUME;
        for (int i = 0; i < elementCount; i++) {
            int digits = digitList[i];
            elementInfos[i] = new DefaultValueJoinFormat.ElementInfo(digits);
            elementInfos[i].setStartIndex(startIndex);
            int loadedDigits = curVolume < digits ? curVolume : digits;
            elementInfos[i].setCurDigits(loadedDigits);
            curVolume -= loadedDigits;
            digits -= loadedDigits;

            if (curVolume == 0) {
                startIndex++;
                curVolume = MAX_VOLUME;
            }

            if (digits != 0) {
                elementInfos[i].setNextDigits(digits);
                curVolume -= digits;
            }
        }
        dataLength = startIndex + 1;
    }

    public class ElementInfo {
        private final int originDigits;
        private final short valueLimitShort;
        private final int valueLimitInteger;
        private final long valueLimitLong;

        private int startIndex;
        private int curDigits;
        private int nextDigits;

        public ElementInfo(int digits) {
            this.originDigits = digits;
            valueLimitShort = getShortLimit(digits);
            valueLimitInteger = getIntegerLimit(digits);
            valueLimitLong = getLongLimit(digits);
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getCurDigits() {
            return curDigits;
        }

        public void setCurDigits(int curDigits) {
            this.curDigits = curDigits;
        }

        public int getNextDigits() {
            return nextDigits;
        }

        public void setNextDigits(int nextDigits) {
            this.nextDigits = nextDigits;
        }

        public int getOriginDigits() {
            return originDigits;
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

        private short getShortLimit(int digits) {
            switch (digits) {
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
                    throw new IllegalArgumentException(String.format("%d digits exceed limit!", digits));
            }
        }

        private int getIntegerLimit(int digits) {
            switch (digits) {
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
                    throw new IllegalArgumentException(String.format("%d digits exceed limit!", digits));
            }
        }

        private long getLongLimit(int digits) {
            switch (digits) {
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
                    throw new IllegalArgumentException(String.format("%d digits exceed limit!", digits));
            }
        }
    }
}
