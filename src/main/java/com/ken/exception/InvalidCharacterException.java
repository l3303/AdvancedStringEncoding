package com.ken.exception;

/**
 * Created by liuken on 2017/12/31.
 */
public class InvalidCharacterException extends StringEncodingException {

    public InvalidCharacterException(String strValue, char letter) {
        super(strValue, "'%s' is not a valid letter!");
    }
}
