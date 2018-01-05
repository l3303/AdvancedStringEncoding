package com.ken.exception;

/**
 * Created by liuken on 2017/12/31.
 */
public class InvalidCharacterException extends StringEncodeException {

    public InvalidCharacterException(String strValue, char letter) {
        super(strValue, String.format("'%c' is not a valid letter!", letter));
    }
}
