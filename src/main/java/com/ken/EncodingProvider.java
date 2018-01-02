package com.ken;

import com.ken.exception.StringEncodingException;
import org.springframework.stereotype.Component;

/**
 * Created by liuken on 2017/12/30.
 */
@Component
public interface EncodingProvider {

    int encodingToInterge(String strValue) throws StringEncodingException;

    int encodingToInterge(String strValue, int startIndex, int count) throws StringEncodingException;

    long encodingToLong(String strValue) throws StringEncodingException;

    long encodingToLong(String strValue, int startIndex, int count) throws StringEncodingException;

    String decodingToString(int value);

    String decodingToString(long value);
}
