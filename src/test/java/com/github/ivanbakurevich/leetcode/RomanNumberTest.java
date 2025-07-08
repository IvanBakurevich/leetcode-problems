package com.github.ivanbakurevich.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumberTest {

    @ParameterizedTest
    @CsvSource({"III,3", "IV,4", "LVIII,58", "MCMXCIV,1994"})
    void testRomanToInt(String roman, int expectedInt) {
        int result = RomanNumber.romanToInt(roman);
        assertEquals(expectedInt, result);

    }
}