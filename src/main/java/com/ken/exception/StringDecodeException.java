package com.ken.exception;

public class StringDecodeException extends Exception {

    public StringDecodeException(long originValue, String errorMsg) {
        super(String.format("%l decode fail! Detail reason: %s", originValue, errorMsg));
    }
}
