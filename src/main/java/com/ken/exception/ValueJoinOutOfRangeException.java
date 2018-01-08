package com.ken.exception;

/**
 * Created by liuken on 2018/1/7.
 */
public class ValueJoinOutOfRangeException extends ValueJoinException {

    public ValueJoinOutOfRangeException(long limit, long actual) {
        super(String.format("%d exceed limit %d!", actual, limit));
    }
}
