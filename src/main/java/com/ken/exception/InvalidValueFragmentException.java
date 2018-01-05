package com.ken.exception;

public class InvalidValueFragmentException extends StringDecodeException {

    public InvalidValueFragmentException(long originValue, int fragment) {
        super(originValue, "%i can not decode to character!");
    }
}
