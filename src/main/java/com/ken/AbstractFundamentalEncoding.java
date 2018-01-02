package com.ken;

import com.ken.exception.InvalidCharacterException;
import com.ken.exception.OutOfRangeException;
import com.ken.exception.StringEncodingException;
import org.springframework.stereotype.Component;

/**
 * Created by liuken on 2018/1/1.
 */
@Component
public abstract class AbstractFundamentalEncoding implements EncodingProvider {

    private final int intergeMaxLetterLength;
    private final int longMaxLetterLength;
    private final int maskInterge;

    public AbstractFundamentalEncoding() {
        int bits = getCharacterBits();
        intergeMaxLetterLength = 31/ bits;
        longMaxLetterLength = 63/ bits;
        int mask = 1;
        for (int i = 1; i < bits; i++) {
            mask += mask*2;
        }
        maskInterge = mask;
    }


    public int encodingToInterge(String strValue) throws StringEncodingException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > intergeMaxLetterLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToInterge(strValue, 0, strValue.length() - 1);
    }

    public int encodingToInterge(String strValue, int startIndex, int count) throws StringEncodingException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > count || strValue.length() < startIndex + count) {
            throw new OutOfRangeException(strValue);
        }
        if (count > intergeMaxLetterLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToInterge(strValue, startIndex, startIndex + count);
    }

    private int doEncodingToInterge(String strValue, int startIndex, int endIndex) throws StringEncodingException {
        char[] letters = strValue.toCharArray();
        int result = 0;
        int bits = getCharacterBits();
        for (; startIndex <= endIndex; startIndex++)
        {
            result <<= bits;
            int tmp = encodingToValueType(letters[startIndex]);
            if (tmp < 0) {
                throw new InvalidCharacterException(strValue, letters[startIndex]);
            }
            result += tmp;
        }
        return result;
    }

    public long encodingToLong(String strValue) throws StringEncodingException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > longMaxLetterLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToLong(strValue, 0, strValue.length() - 1);
    }

    public long encodingToLong(String strValue, int startIndex, int count) throws StringEncodingException {
        if (strValue == null || strValue.length() == 0) {
            return 0;
        }
        if (strValue.length() > count || strValue.length() < startIndex + count) {
            throw new OutOfRangeException(strValue);
        }
        if (count > longMaxLetterLength) {
            throw new OutOfRangeException(strValue);
        }
        return doEncodingToLong(strValue, startIndex, startIndex + count);
    }

    private long doEncodingToLong(String strValue, int startIndex, int endIndex) throws StringEncodingException {
        char[] letters = strValue.toCharArray();
        long result = 0L;
        int bits = getCharacterBits();
        for (; startIndex <= endIndex; startIndex++)
        {
            result <<= bits;
            int tmp = encodingToValueType(letters[startIndex]);
            if (tmp < 0) {
                throw new InvalidCharacterException(strValue, letters[startIndex]);
            }
            result += tmp;
        }
        return result;
    }

    public String decodingToString(int value) {
        if (value == 0) {
            return "";
        }

        int bits = getCharacterBits();
        int length = 0;
        int tmp = value;
        while (tmp != 0) {
            length++;
            tmp >>= bits;
        }
        char[] letters = new char[length];
        for (int i = 0; i < length; i++) {
            int tmpValue = value & maskInterge;
            letters[i] = decodingToCharacter(tmpValue);
            value >>= bits;
        }
        return new String(letters);
    }

    public String decodingToString(long value) {
        if (value == 0L) {
            return "";
        }

        int bits = getCharacterBits();
        int length = 0;
        long tmp = value;
        while (tmp != 0L) {
            length++;
            tmp >>= bits;
        }
        char[] letters = new char[length];
        for (int i = 0; i < length; i++) {
            int tmpValue = (int)value & maskInterge;
            letters[i] = decodingToCharacter(tmpValue);
            value >>= bits;
        }
        return new String(letters);
    }

    protected abstract int getCharacterBits();

    /**
     * Convert letter to int value
     * Transfer fail will return a negative value
     * @param letter
     * @return
     */
    protected abstract int encodingToValueType(char letter);

    protected abstract char decodingToCharacter(int value);
}
