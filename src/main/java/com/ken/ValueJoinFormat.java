package com.ken;

import com.ken.exception.ValueJoinException;

/**
 * Created by liuken on 2018/1/1.
 */
public interface ValueJoinFormat {

    int getElementCount();

    int[] encodeDigitList();
}
