package com.ken.exception;

import com.ken.config.StringEncodingConfig;

/**
 * Created by liuken on 2017/12/30.
 */
public class OutOfRangeException extends StringEncodingException {

    public OutOfRangeException(String strValue) {
        super(strValue, "Too long to encoding in a value type!");
    }
}
