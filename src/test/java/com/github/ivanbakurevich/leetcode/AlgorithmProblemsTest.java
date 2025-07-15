package com.github.ivanbakurevich.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmProblemsTest {

    @ParameterizedTest
    @MethodSource("testTwoSumProvider")
    void testTwoSum(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, AlgorithmProblems.twoSum(nums, target));
    }

    private static Stream<Arguments> testTwoSumProvider() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("reverseTestProvider")
    void testReverse(int param, int expected) {
        assertEquals(expected, AlgorithmProblems.reverse(param));
    }

    private static Stream<Arguments> reverseTestProvider() {
        return Stream.of(
                Arguments.of(123, 321),
                Arguments.of(321, 123),
                Arguments.of(Integer.MIN_VALUE, 0)
        );
    }
}