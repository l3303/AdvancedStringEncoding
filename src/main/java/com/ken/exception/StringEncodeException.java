package com.ken.exception;

/**
 * Created by liuken on 2018/1/1.
 */
public class StringEncodeException extends Exception {

    public StringEncodeException(String strValue, String errorMsg) {
        super(String.format("%s encode fail! Detail reason: %s", strValue, errorMsg));
    }
}
