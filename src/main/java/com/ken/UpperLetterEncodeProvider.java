package com.ken;

import org.springframework.stereotype.Component;

/**
 * Created by liuken on 2017/12/30.
 * Transfer Upper letters to value type
 */
@Component
public class UpperLetterEncodeProvider extends AbstractBasicEncodeProvider {

    private static final int LETTER_DIGIT = 5;

    @Override
    protected int getCharacterBits() {
        return LETTER_DIGIT;
    }

    /**
     * Convert letter to int value, note: return value will minus offset 64
     * eg. 'A' will transfer to 1
     * @param letter
     * @return
     */
    @Override
    protected int encodeToInteger(char letter) {
        int origin = (int) letter;
        if (origin > 64 && origin < 91) {
            return origin - 64;
        } else {
            return -1;
        }
    }

    @Override
    protected char decodeToCharacter(int value) {
        return (char)(value + 64);
    }
}
