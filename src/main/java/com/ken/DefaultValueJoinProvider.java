package com.ken;

import com.ken.exception.*;

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
        if (format == null || valueList ==null || valueList.length == 0) {
            return null;
        }
        int elementCount = valueList.length;
        int count = format.getElementCount();
        if (count != elementCount) {
            throw new ValueCountNotMatchException(count, elementCount);
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
        if (format == null || valueList ==null || valueList.length == 0) {
            return null;
        }
        int elementCount = valueList.length;
        int count = format.getElementCount();
        if (count != elementCount) {
            throw new ValueCountNotMatchException(count, elementCount);
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
        if (format == null || valueList ==null || valueList.length == 0) {
            return null;
        }
        int elementCount = valueList.length;
        int count = format.getElementCount();
        if (count != elementCount) {
            throw new ValueCountNotMatchException(count, elementCount);
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
    public long[] split(ValueJoinFormat format, Object obj) throws ValueSplitException {
        if (format == null || obj == null) {
            return null;
        }
        if (!(obj instanceof VariableValue)) {
            throw new ValueSplitNotSupportedException(obj);
        }
        VariableValue value = (VariableValue) obj;
        DefaultValueJoinFormat internalFormat = getInternalFormat(format);
        long[] data = value.getData();
        if (data.length != internalFormat.getDataLength()) {
            throw new ValueSplitNotSupportedException(obj);
        }

        int elementCount = internalFormat.getElementCount();
        long[] results = new long[elementCount];
        for (int i = 0; i < elementCount; i++) {
            results[i] = extractValue(data, internalFormat.getElementInfo(i));
        }
        return results;
    }

    private void insertValue(long[] data, DefaultValueJoinFormat.ElementInfo info, long value) {
        int index = info.getStartIndex();
        int curDigits = info.getCurDigits();
        int offset = info.getOffset();
        int nextDigits = info.getNextDigits();
        int nextOffset = info.getNextOffset();
        if (nextDigits == 0) {
            data[index] += (value << offset);
        } else {
            data[index] += ((value & getMask(curDigits)) << offset);
            index++;
            data[index] += ((value >> curDigits) << nextOffset);
        }
    }

    private long extractValue(long[] data, DefaultValueJoinFormat.ElementInfo info) {
        int index = info.getStartIndex();
        int nextDigits = info.getNextDigits();
        int curDigits = info.getCurDigits();
        int offset = info.getOffset();
        int nextOffset = info.getNextOffset();
        if (nextDigits == 0) {
            return (data[index] >>> offset) & getMask(curDigits);
        } else {
            //lower digits + higher digits
            return ((data[index] >> offset) & getMask(curDigits)) +
                    (((data[++index] >> nextOffset) & getMask(nextDigits)) << curDigits);
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
