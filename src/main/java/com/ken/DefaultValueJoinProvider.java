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
        DefaultValueJoinFormat internalFormat = getInternalFormat(format);
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
        DefaultValueJoinFormat internalFormat = getInternalFormat(format);
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
        DefaultValueJoinFormat internalFormat = getInternalFormat(format);
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

    @Override
    public long[] split(ValueJoinFormat format, Object obj) throws ValueJoinException {
        if (!(obj instanceof VariableValue)) {
            //TODO: 异常处理
            return null;
        }
        DefaultValueJoinFormat internalFormat = getInternalFormat(format);

        VariableValue value = (VariableValue) obj;
        long[] datas = value.getData();
        if (datas.length != internalFormat.getDataLength()) {
            return null;
        }

        int elementCount = internalFormat.getElementCount();
        long[] results = new long[elementCount];
        for (int i = 0; i < elementCount; i++) {
            results[i] = extractValue(datas, internalFormat.getElementInfo(i));
        }
        return results;
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

    private long extractValue(long[] data, DefaultValueJoinFormat.ElementInfo info) {
        int index = info.getStartIndex();
        int nextDigits = info.getNextDigits();
        int curDigits = info.getCurDigits();
        int offset = info.getOffset();
        if (nextDigits == 0) {
            return (data[index] >>> offset) & getMask(curDigits);
        } else {
            long a = (data[index] >>> offset);
            long b = a & getMask(curDigits);
            long val = ((data[index] >>> offset) & getMask(curDigits)) << nextDigits;
            val += data[index++] & getMask(nextDigits);
            return ((data[index] >>> offset) & getMask(curDigits)) + ((data[index++] & getMask(nextDigits)) << curDigits);
        }
    }

    private DefaultValueJoinFormat getInternalFormat(ValueJoinFormat format) {
        if (format instanceof DefaultValueJoinFormat) {
            return  (DefaultValueJoinFormat) format;
        } else {
            if (formatMap.containsKey(format)) {
                return formatMap.get(format);
            } else {
                DefaultValueJoinFormat internalFormat = new DefaultValueJoinFormat(format);
                formatMap.put(format, internalFormat);
                return internalFormat;
            }
        }
    }

    private long getMask(int digits) {
        return (1L << digits) - 1;
    }
}
