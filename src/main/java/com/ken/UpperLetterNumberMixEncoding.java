package com.ken;

import org.springframework.stereotype.Component;

/**
 * Created by liuken on 2017/12/30.
 */
@Component
public class UpperLetterNumberMixEncoding extends AbstractFundamentalEncoding {

    private static final int LETTER_DIGIT = 6;

    protected int getCharacterBits() {
        return LETTER_DIGIT;
    }

    /**
     * Convert letter to int value, note: return value will minus offset
     * eg. '0' will transfer to 1
     * eg. 'A' will transfer to 11
     * @param letter
     * @return
     */
    protected int encodingToValueType(char letter)
    {
        int origin = (int) letter;
        if (origin > 47 && origin < 58) {
            return origin - 47;
        } else if (origin > 64 && origin < 91) {
            return origin - 54;
        } else {
            return -1;
        }
    }

    protected char decodingToCharacter(int value) {
        if (value > 0 && value < 11) {
            return (char)(value + 47);
        } else if (value > 10 && value < 37) {
            return (char)(value + 54);
        } else {
            return ' ';
        }
    }
}
