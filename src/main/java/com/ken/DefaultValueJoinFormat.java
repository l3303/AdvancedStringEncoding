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
            if (digits < 0 || digits > MAX_VOLUME) {
                throw new IllegalArgumentException(String.format("%d digits exceed limit!", digits));
            } else {
                digits = digits < Short.SIZE ? digits : Short.SIZE - 1;
                return (short) ((1 << digits) - 1);
            }
        }

        private int getIntegerLimit(int digits) {
            if (digits < 0 || digits > MAX_VOLUME) {
                throw new IllegalArgumentException(String.format("%d digits exceed limit!", digits));
            } else {
                digits = digits < Integer.SIZE ? digits : Integer.SIZE - 1;
                return (1 << digits) - 1;
            }
        }

        private long getLongLimit(int digits) {
            if (digits < 0 || digits > MAX_VOLUME) {
                throw new IllegalArgumentException(String.format("%d digits exceed limit!", digits));
            } else {
                digits = digits < Long.SIZE ? digits : Long.SIZE - 1;
                return (1L << digits) - 1L;
            }
        }
    }
}
