package com.ken;

/**
 * Created by liuken on 2017/12/30.
 */
public class DefaultEncodeProvider extends AbstractBasicEncodeProvider {

    private static final int LETTER_DIGIT = 6;
    private static final int ENCODE_TABLE_COUNT = 127;
    private static final int DECODE_TABLE_COUNT = 37;

    public static final int[] encode_table = new int[ENCODE_TABLE_COUNT];

    public static final char[] decode_table = new char[] {
            '\0',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
    };

    static {
        for (int i = 0; i < encode_table.length; i++) {
            encode_table[i] = -1;
        }
        for (int i = 1; i < decode_table.length; ++i) {
            encode_table[decode_table[i]] = i;
        }
    }

    @Override
    protected int getCharacterBits() {
        return LETTER_DIGIT;
    }

    @Override
    protected int encodeToInteger(char letter) {
        int value = letter;
        if (value > 0 && value < ENCODE_TABLE_COUNT) {
            return encode_table[value];
        } else {
            return -1;
        }
    }

    @Override
    protected char decodeToCharacter(int value) {
        if (value > 0 && value < DECODE_TABLE_COUNT) {
            return decode_table[value];
        } else {
            return Character.MIN_VALUE;
        }
    }
}
