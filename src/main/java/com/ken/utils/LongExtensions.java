package com.ken.utils;

public class LongExtensions {

    private static final int HEX_STRING_SHIFT = 4;
    private static final int HEX_STRING_MASK = 15;
    public static final int HEX_STRING_LENGTH = 16;

    private final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'A' , 'B' ,
            'C' , 'D' , 'E' , 'F'
    };

    public static int fillInHexCharacters(char[] buf, long val, int offset) {
        int charPos = HEX_STRING_LENGTH;
        do {
            buf[offset + --charPos] = digits[((int) val) & HEX_STRING_MASK];
            val >>>= HEX_STRING_SHIFT;
        } while (charPos > 0);

        return offset + HEX_STRING_LENGTH;
    }
}
