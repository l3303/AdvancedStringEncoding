package com.ken;

import com.ken.exception.ValueCountNotMatchException;
import com.ken.exception.ValueJoinException;
import com.ken.exception.ValueJoinOutOfRangeException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liuken on 2018/1/7.
 */
public class DefaultValueJoinProvider implements ValueJoinProvider {

    /**
    * Cache other ValueJoinFormat instances convert to DefaultValueJoinFormat
    * ConcurrentHashMap ensure thread-safe
    */
    private static final ConcurrentHashMap<ValueJoinFormat, DefaultValueJoinFormat> formatMap = new ConcurrentHashMap<ValueJoinFormat, DefaultValueJoinFormat>();

    @Override
    public Object join(ValueJoinFormat format, short... valueList) throws ValueJoinException {
        int elementCount = valueList.length;
        if (format.getElementCount() != elementCount) {
            throw new ValueCountNotMatchException(elementCount, valueList.length);
        }
        DefaultValueJoinFormat internalFormat;
        if (format instanceof DefaultValueJoinFormat) {
            internalFormat = (DefaultValueJoinFormat) format;
        } else {
            internalFormat = getInternalFormat(format);
        }
        long[] data = internalFormat.createDatas();
        for (int i = 0; i < elementCount; i++) {
            short value = valueList[i];
            DefaultValueJoinFormat.ElementInfo info = internalFormat.getElementInfo(i);
            if (info.getValueLimitShort() < value) {
                throw new ValueJoinOutOfRangeException(info.getValueLimitShort(), value);
            }
            insertValue(data, info, (long) value);
        }

        return new VariableValue(data);
    }

    @Override
    public Object join(ValueJoinFormat format, int... valueList) throws ValueJoinException {
        int elementCount = valueList.length;
        if (format.getElementCount() != valueList.length) {
            throw new ValueCountNotMatchException(elementCount, valueList.length);
        }

        DefaultValueJoinFormat internalFormat;
        if (format instanceof DefaultValueJoinFormat) {
            internalFormat = (DefaultValueJoinFormat) format;
        } else {
            internalFormat = getInternalFormat(format);
        }

        long[] data = internalFormat.createDatas();
        for (int i = 0; i < elementCount; i++) {
            int value = valueList[i];
            DefaultValueJoinFormat.ElementInfo info = internalFormat.getElementInfo(i);
            if (info.getValueLimitInteger() < value) {
                throw new ValueJoinOutOfRangeException(info.getValueLimitInteger(), value);
            }
            insertValue(data, info, (long) value);
        }

        return new VariableValue(data);
    }

    @Override
    public Object join(ValueJoinFormat format, long... valueList) throws ValueJoinException {
        int elementCount = valueList.length;
        if (format.getElementCount() != valueList.length) {
            throw new ValueCountNotMatchException(elementCount, valueList.length);
        }

        DefaultValueJoinFormat internalFormat;
        if (format instanceof DefaultValueJoinFormat) {
            internalFormat = (DefaultValueJoinFormat) format;
        } else {
            internalFormat = getInternalFormat(format);
        }

        long[] data = internalFormat.createDatas();
        for (int i = 0; i < elementCount; i++) {
            long value = valueList[i];
            DefaultValueJoinFormat.ElementInfo info = internalFormat.getElementInfo(i);
            if (info.getValueLimitLong() < value) {
                throw new ValueJoinOutOfRangeException(info.getValueLimitLong(), value);
            }
            insertValue(data, info, value);
        }

        return new VariableValue(data);
    }

    private void insertValue(long[] data, DefaultValueJoinFormat.ElementInfo info, long value) {
        int index = info.getStartIndex();
        int nextDigits = info.getNextDigits();
        int curDigits = info.getCurDigits();
        data[index] <<= curDigits;
        if (nextDigits == 0) {
            data[index] += value;
        } else {
            data[index] += value & getMask(curDigits);
            index++;
            data[index] <<= nextDigits;
            data[index] += value >> curDigits;
        }
    }

    private DefaultValueJoinFormat getInternalFormat(ValueJoinFormat format) {
        if (formatMap.containsKey(format)) {
            return formatMap.get(format);
        } else {
            DefaultValueJoinFormat internalFormat = new DefaultValueJoinFormat(format);
            formatMap.put(format, internalFormat);
            return internalFormat;
        }
    }

    private long getMask(int digits) {
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
                //should not be here!
                return 0L;
        }
    }
}
