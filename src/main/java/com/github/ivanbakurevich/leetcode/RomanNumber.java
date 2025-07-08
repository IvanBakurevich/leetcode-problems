package com.github.ivanbakurevich.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Leetcode problem <a href="https://leetcode.com/problems/roman-to-integer">link</a>
 */
public class RomanNumber {

    public static void main(String[] args) {
        romanToInt("MCMXCIV");
    }

    private static final Logger log = LoggerFactory.getLogger(RomanNumber.class);

    private static final Map<Character, Integer> romanSymbolMapping = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public static int romanToInt(String romanNumber) {
        int result = 0;

        for (int i = 0; i < romanNumber.length(); i++) {
            if (i < romanNumber.length() - 1
                    && romanSymbolMapping.get(romanNumber.charAt(i)) < romanSymbolMapping.get(romanNumber.charAt(i + 1))) {
                result -= romanSymbolMapping.get(romanNumber.charAt(i));
            } else {
                result += romanSymbolMapping.get(romanNumber.charAt(i));
            }
        }

        return result;
    }

    public static int romanToIntOldImpl(String romanNumber) {
        int result = 0;
        int prev = romanSymbolMapping.get(romanNumber.charAt(0));
        int current;

        log.info("Roman number: {}", romanNumber);

        if (romanNumber.length() == 1) {
            return prev;
        }

        for (int i = 1; i < romanNumber.length(); i++) {
            current = romanSymbolMapping.get(romanNumber.charAt(i));
            log.debug("Iteration {}", i);
            log.debug("Result: {}", result);
            log.debug("Current symbol: {}", current);
            log.debug("Previous symbol: {}", prev);

            if (prev == 0) {
                //skip
            } else if (prev >= current) {
                result += prev;
            } else {
                result += current - prev;
                current = 0;
            }

            if (i == romanNumber.length() - 1) {
                result += current;
            }

            log.debug("Current symbol after: {}", current);
            log.debug("Previous symbol after: {}", prev);
            log.debug("Result after: {}", result);
            log.debug("---------------");

            prev = current;
        }

        log.info("Final result: {}", result);
        return result;

    }

}
