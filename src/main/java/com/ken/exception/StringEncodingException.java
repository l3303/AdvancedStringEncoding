package com.ken.exception;

/**
 * Created by liuken on 2018/1/1.
 */
public class StringEncodingException extends Exception {

    public StringEncodingException(String strValue, String errorMsg) {
        super(String.format("%s encoding fail! Detail reason: %s", strValue, errorMsg));
    }
}
