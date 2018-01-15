package com.ken.utils;

public class LongExtensions {

    private static final int HEX_STRING_SHIFT = 4;
    private static final int HEX_STRING_MASK = 15;
    public static final int HEX_STRING_LENGTH = 16;
    private static final int ENCODE_TABLE_COUNT = 127;

    private final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'A' , 'B' ,
            'C' , 'D' , 'E' , 'F'
    };

    private final static long[] encode_table;

    static {
        encode_table = new long[ENCODE_TABLE_COUNT];
        for (int i = 0; i < encode_table.length; i++) {
            encode_table[i] = -1;
        }
        for (int i = 0; i < digits.length; ++i) {
            encode_table[digits[i]] = (long) i;
        }
    }

    public static int fillInHexCharacters(char[] buf, long val, int offset) {
        int charPos = HEX_STRING_LENGTH;
        do {
            buf[offset + --charPos] = digits[((int) val) & HEX_STRING_MASK];
            val >>>= HEX_STRING_SHIFT;
        } while (charPos > 0);

        return offset + HEX_STRING_LENGTH;
    }

    public static long parseLongFromHexCharacters(char[] charList, int offset) {
        if (offset + HEX_STRING_LENGTH > charList.length) {
            //TODO
            return 0;
        }
        long result = 0;
        for (int i = 0; i < HEX_STRING_LENGTH; i++) {
            long tmp = encode_table[(int) charList[offset + i]];
            if (tmp < 0) {
                //TODO
            }
            result <<= HEX_STRING_SHIFT;
            result += tmp;
        }
        return result;
    }
}
