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
            int dataIndex = info.getDataIndex();
            data[dataIndex] += (long) value;
            data[dataIndex] <<= info.getDigit();
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
            int dataIndex = info.getDataIndex();
            data[dataIndex] += (long) value;
            data[dataIndex] <<= info.getDigit();
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
            int dataIndex = info.getDataIndex();
            data[dataIndex] += value;
            data[dataIndex] <<= info.getDigit();
        }

        return new VariableValue(data);
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
}
