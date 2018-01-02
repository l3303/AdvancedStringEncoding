package com.ken;

import com.ken.exception.StringEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liuken on 2018/1/1.
 */
@Component
public class AbstractSequencialEncodingProvider implements EncodingProvider {

//    @Autowired
//    private StringFormat format;

    public AbstractSequencialEncodingProvider() {

    }

    public int encodingToInterge(String strValue) throws StringEncodingException {
        return 0;
    }

    public int encodingToInterge(String strValue, int startIndex, int count) throws StringEncodingException {
        return 0;
    }

    public long encodingToLong(String strValue) throws StringEncodingException {
        return 0;
    }

    public long encodingToLong(String strValue, int startIndex, int count) throws StringEncodingException {
        return 0;
    }

    public String decodingToString(int value) {
        return null;
    }

    public String decodingToString(long value) {
        return null;
    }
}
