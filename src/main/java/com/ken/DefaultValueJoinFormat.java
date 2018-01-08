package com.ken;

import java.lang.reflect.Array;

/**
 * Created by liuken on 2018/1/8.
 */
public class DefaultValueJoinFormat implements ValueJoinFormat {
    private final DefaultValueJoinFormat.ElementInfo[] elementInfos;
    private final int dataLength;
    private final int[] digitList;

    public DefaultValueJoinFormat(int... digitList) {
        this.digitList = digitList;
        dataLength = digitList.length;
        elementInfos = new ElementInfo[dataLength];
        intial();

    }

    public DefaultValueJoinFormat(ValueJoinFormat format) {
        this.digitList = format.encodeDigitList();
        dataLength = this.digitList.length;
        intial();
    }

    @Override
    public int getElementCount() {
        return dataLength;
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

    private void intial() {
        //TODO: 计算出最优的放置方案
        
    }

    public class ElementInfo {
        private int dataIndex;
        private int digit;
        private long valueLimitShort;
        private long valueLimitInteger;
        private long valueLimitLong;

        public int getDataIndex() {
            return dataIndex;
        }

        public void setDataIndex(int dataIndex) {
            this.dataIndex = dataIndex;
        }

        public int getDigit() {
            return digit;
        }

        public void setDigit(int digit) {
            this.digit = digit;
        }

        public long getValueLimitShort() {
            return valueLimitShort;
        }

        public void setValueLimitShort(long valueLimitShort) {
            this.valueLimitShort = valueLimitShort;
        }

        public long getValueLimitInteger() {
            return valueLimitInteger;
        }

        public void setValueLimitInteger(long valueLimitInteger) {
            this.valueLimitInteger = valueLimitInteger;
        }

        public long getValueLimitLong() {
            return valueLimitLong;
        }

        public void setValueLimitLong(long valueLimitLong) {
            this.valueLimitLong = valueLimitLong;
        }
    }
}
