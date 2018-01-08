package com.ken.exception;

/**
 * Created by liuken on 2018/1/7.
 */
public class ValueCountNotMatchException extends ValueJoinException {
    public ValueCountNotMatchException(int formatCount, int actualCount) {
        super(String.format("Format define %d elements, input parameters contains %s elements!", formatCount, actualCount));
    }
}
