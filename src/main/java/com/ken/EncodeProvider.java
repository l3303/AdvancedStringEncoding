package com.ken;

import com.ken.exception.StringDecodeException;
import com.ken.exception.StringEncodeException;

/**
 * Created by liuken on 2017/12/30.
 */
public interface EncodeProvider {

    short encodeToShort(String strValue) throws StringEncodeException;

    short encodeToShort(String strValue, int startIndex, int count) throws StringEncodeException;

    int encodeToInteger(String strValue) throws StringEncodeException;

    int encodeToInteger(String strValue, int startIndex, int count) throws StringEncodeException;

    long encodeToLong(String strValue) throws StringEncodeException;

    long encodeToLong(String strValue, int startIndex, int count) throws StringEncodeException;

    String decodeToString(short value) throws StringDecodeException;

    String decodeToString(int value) throws StringDecodeException;

    String decodeToString(long value) throws StringDecodeException;

    int getStringLength(short value);

    int getStringLength(int value);

    int getStringLength(long value);
}
