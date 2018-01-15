package com.ken;

import com.ken.exception.ValueJoinException;
import com.ken.exception.ValueSplitException;

/**
 * Created by liuken on 2018/1/7.
 */
public interface ValueJoinProvider {

    Object join(ValueJoinFormat format, short... valueList) throws ValueJoinException;

    Object join(ValueJoinFormat format, int... valueList) throws ValueJoinException;

    Object join(ValueJoinFormat format, long... valueList) throws ValueJoinException;

    long[] split(ValueJoinFormat format, Object obj) throws ValueSplitException;
}
