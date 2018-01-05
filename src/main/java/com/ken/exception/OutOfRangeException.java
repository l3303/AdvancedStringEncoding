package com.ken.exception;

/**
 * Created by liuken on 2017/12/30.
 */
public class OutOfRangeException extends StringEncodeException {

    public OutOfRangeException(String strValue) {
        super(strValue, "Too long to encode!");
    }
}
