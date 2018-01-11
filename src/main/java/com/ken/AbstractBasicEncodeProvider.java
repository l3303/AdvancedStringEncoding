package com.ken;

import com.ken.exception.*;

/**
 * Created by liuken on 2018/1/1.
 */
public abstract class AbstractBasicEncodeProvider implements EncodeProvider {

    private final int shortMaxCharLength;
    private final int integerMaxCharLength;
    private final int longMaxCharLength;
    private final int decodeMask;

    public AbstractBasicEncodeProvider() {
        int bits = getCharacterBits();
        shortMaxCharLength = 15/bits;
        integerMaxCharLength = 31/ bits;
        longMaxCharLength = 63/ bits;
        int mask = 1;
        int tmp = 1;
        for (int i = 1; i < bits; i++) {
            tmp *= 2;
            mask += tmp;
        }
        decodeMask = mask;
    }

    @Override
    public short encodeToShort(String strValue) throws StringEncodeException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > shortMaxCharLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToShort(strValue, 0, strValue.length() - 1);
    }

    @Override
    public short encodeToShort(String strValue, int startIndex, int count) throws StringEncodeException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > count || strValue.length() < startIndex + count) {
            throw new OutOfRangeException(strValue);
        }
        if (count > shortMaxCharLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToShort(strValue, startIndex, startIndex + count);
    }

    private short doEncodingToShort(String strValue, int startIndex, int endIndex) throws StringEncodeException {
        short result = 0;
        int bits = getCharacterBits();
        for (; startIndex <= endIndex; startIndex++) {
            result <<= bits;
            short tmp = (short) encodeToInteger(strValue.charAt(startIndex));
            if (tmp < 0) {
                throw new InvalidCharacterException(strValue, strValue.charAt(startIndex));
            }
            result += tmp;
        }
        return result;
    }

    @Override
    public int encodeToInteger(String strValue) throws StringEncodeException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > integerMaxCharLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToInteger(strValue, 0, strValue.length() - 1);
    }

    @Override
    public int encodeToInteger(String strValue, int startIndex, int count) throws StringEncodeException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > count || strValue.length() < startIndex + count) {
            throw new OutOfRangeException(strValue);
        }
        if (count > integerMaxCharLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToInteger(strValue, startIndex, startIndex + count);
    }

    private int doEncodingToInteger(String strValue, int startIndex, int endIndex) throws StringEncodeException {
        int result = 0;
        int bits = getCharacterBits();
        for (; startIndex <= endIndex; startIndex++) {
            result <<= bits;
            int tmp = encodeToInteger(strValue.charAt(startIndex));
            if (tmp < 0) {
                throw new InvalidCharacterException(strValue, strValue.charAt(startIndex));
            }
            result += tmp;
        }
        return result;
    }

    @Override
    public long encodeToLong(String strValue) throws StringEncodeException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > longMaxCharLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToLong(strValue, 0, strValue.length() - 1);
    }

    @Override
    public long encodeToLong(String strValue, int startIndex, int count) throws StringEncodeException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > count || strValue.length() < startIndex + count) {
            throw new OutOfRangeException(strValue);
        }
        if (count > longMaxCharLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToLong(strValue, startIndex, startIndex + count);
    }

    private long doEncodingToLong(String strValue, int startIndex, int endIndex) throws StringEncodeException {
        long result = 0L;
        int bits = getCharacterBits();
        for (; startIndex <= endIndex; startIndex++) {
            result <<= bits;
            long tmp = (long) encodeToInteger(strValue.charAt(startIndex));
            if (tmp < 0) {
                throw new InvalidCharacterException(strValue, strValue.charAt(startIndex));
            }
            result += tmp;
        }
        return result;
    }

    @Override
    public String decodeToString(short value) throws StringDecodeException {
        if (value == 0) {
            return "";
        }

        int valueInt = (int) value;
        int bits = getCharacterBits();
        int length = getStringLength(value);
        char[] characters = new char[length];
        for (int i = length; i > 0; i--) {
            int tmpValue = valueInt & decodeMask;
            char tmpChar = decodeToCharacter(tmpValue);
            if (tmpChar == Character.MIN_VALUE) {
                throw new InvalidValueFragmentException((long) value, tmpValue);
            }
            characters[i - 1] = tmpChar;
            value >>= bits;

        }
        return new String(characters);
    }

    @Override
    public String decodeToString(int value) throws StringDecodeException {
        if (value == 0) {
            return "";
        }

        int bits = getCharacterBits();
        int length = getStringLength(value);
        char[] characters = new char[length];
        for (int i = length; i > 0; i--) {
            int tmpValue = value & decodeMask;
            char tmpChar = decodeToCharacter(tmpValue);
            if (tmpChar == Character.MIN_VALUE) {
                throw new InvalidValueFragmentException((long) value, tmpValue);
            }
            characters[i - 1] = tmpChar;
            value >>= bits;
        }
        return new String(characters);
    }

    @Override
    public String decodeToString(long value) throws StringDecodeException {
        if (value == 0L) {
            return "";
        }

        int bits = getCharacterBits();
        int length = getStringLength(value);
        char[] characters = new char[length];
        for (int i = length; i > 0; i--) {
            int tmpValue = (int)value & decodeMask;
            char tmpChar = decodeToCharacter(tmpValue);
            if (tmpChar == Character.MIN_VALUE) {
                throw new InvalidValueFragmentException(value, tmpValue);
            }
            characters[i - 1] = tmpChar;
            value >>= bits;
        }
        return new String(characters);
    }

    @Override
    public int getStringLength(short value) {
        int bits = getCharacterBits();
        int length = 0;
        while (value != 0) {
            length++;
            value >>= bits;
        }
        return length;
    }

    @Override
    public int getStringLength(int value) {
        int bits = getCharacterBits();
        int length = 0;
        while (value != 0) {
            length++;
            value >>= bits;
        }
        return length;
    }

    @Override
    public int getStringLength(long value) {
        int bits = getCharacterBits();
        int length = 0;
        while (value != 0L) {
            length++;
            value >>= bits;
        }
        return length;
    }

    protected abstract int getCharacterBits();

    /**
     * Convert letter to int value
     * @param letter
     * @return negative value means encode fail
     */
    protected abstract short encodeToInteger(char letter);

    protected abstract char decodeToCharacter(int value);
}
