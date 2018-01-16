package com.ken.exception;

public class ValueSplitNotSupportedException extends ValueSplitException {
    public ValueSplitNotSupportedException(Object obj) {
        super(String.format("%s cannot split!", obj));
    }
}
