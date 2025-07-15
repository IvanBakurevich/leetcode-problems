package com.github.ivanbakurevich.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class AlgorithmProblems {

    private static final Logger log = LoggerFactory.getLogger(AlgorithmProblems.class);

    /**
     * Solution to <a href="https://leetcode.com/problems/two-sum/">...</a>
     */
    public static int[] twoSum(int[] nums, int target) {
        var tempMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int requiredDiff = target - value;
            if (tempMap.containsKey(requiredDiff)) {
                return new int[]{tempMap.get(requiredDiff), i};
            }
            tempMap.put(value, i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Solution to <a href="https://leetcode.com/problems/add-two-numbers/">...</a>
     */
    public static ListNode addTwoNumbers(ListNode list1, ListNode list2) {
        int overflow = 0;
        int list1Val = list1.val;
        int list2Val = list2.val;

        int value = list1Val + list2Val + overflow;
        overflow = value / 10;
        value = value % 10;
        var result = new ListNode(value);
        var resultPointer = result;

        while (list1 != null && list1.next != null || list2 != null && list2.next != null || overflow != 0) {
            list1 = list1 != null ? list1.next : new ListNode(0);
            list2 = list2 != null ? list2.next : new ListNode(0);

            list1Val = list1 != null ? list1.val : 0;
            list2Val = list2 != null ? list2.val : 0;

            value = list1Val + list2Val + overflow;
            overflow = value / 10;
            value = value % 10;

            resultPointer.next = new ListNode(value);
            resultPointer = resultPointer.next;

        }
        return result;
    }


    /**
     * Solution to <a href="https://leetcode.com/problems/merge-strings-alternately">...</a>
     */
    public static String mergeAlternately(String word1, String word2) {
        var builder = new StringBuilder();
        for (var i = 0; i < word1.length() || i < word2.length(); i++) {
            if (i < word1.length()) {
                builder.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                builder.append(word2.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * Solution to <a href="https://leetcode.com/problems/find-pivot-index">...</a>
     */
    public static int pivotIndex(int[] nums) {
        long sum = 0L;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        long rightSum = sum;
        long leftSum = 0L;

        for (int i = 0; i < nums.length; i++) {
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }

    /**
     * Solution to <a href="https://leetcode.com/problems/longest-common-prefix/">...</a>
     */
    public static String longestCommonPrefix(String[] strs) {
        String currentString = strs[0];
        for (int i = 1; i < strs.length; i++) {
            currentString = findCommon(currentString, strs[i]);
            if (currentString.isEmpty()) {
                break;
            }
        }
        return currentString;
    }

    private static String findCommon(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int index = 0;

        while (index < minLength && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }

        return str1.substring(0, index);
    }

    public static int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;
        Map<Integer, Integer> numsCount = new HashMap<>();
        for (int el : nums) {
            Integer countValue = numsCount.merge(el, 1, Integer::sum);
            if (countValue > majorityCount) {
                return el;
            }
        }
        throw new IllegalArgumentException("No majority element found");
    }

    public static boolean isValidSudoku(char[][] board) {
        Set<String> existenElements = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    String elementRow = "r" + i + board[i][j];
                    String elementCol = "c" + j + board[i][j];
                    String elementBox = "b" + i / 3 + j / 3 + board[i][j];
                    if (!existenElements.add(elementRow) || !existenElements.add(elementCol) || !existenElements.add(elementBox)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> symbols = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            symbols.merge(magazine.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            var symbol = ransomNote.charAt(i);
            var res = symbols.computeIfPresent(symbol, (key, oldValue) -> oldValue - 1);
            if (res == null || res < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> symbolMapping = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            var symbol = s.charAt(i);
            var expectedSymbol = t.charAt(i);
            var prev = symbolMapping.put(symbol, expectedSymbol);
            if (prev != null && prev != expectedSymbol) {
                return false;
            }
        }
        return true;
    }

    public static boolean wordPattern(String pattern, String text) {
        var words = text.split(" ");
        var chars = pattern.toCharArray();
        if (chars.length != words.length) {
            return false;
        }

        Map<Character, String> patternWordMapping = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            var prev = patternWordMapping.put(chars[i], words[i]);
            if (prev != null && !prev.equals(words[i])) {
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramMapping = new HashMap<>();
        for (String word : strs) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);
            if (!anagramMapping.containsKey(sortedWord)) {
                anagramMapping.put(sortedWord, new ArrayList<>());
            }
            anagramMapping.get(sortedWord).add(word);
        }
        return new ArrayList<>(anagramMapping.values());
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> charCounter = new HashMap<>();

        if (s.length() != t.length()) {
            return false;
        }
        for (char ch : s.toCharArray()) {
            charCounter.merge(ch, 1, Integer::sum);
        }

        for (char ch : t.toCharArray()) {
            var res = charCounter.computeIfPresent(ch, (k, v) -> v - 1);
            if (res == null || res < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHappy(int number) {
        Set<Integer> visited = new HashSet<>();
        visited.add(number);

        while (true) {
            number = process(number);
            if (number == 1) {
                return true;
            }
            if (visited.contains(number)) {
                return false;
            }
            visited.add(number);
        }
    }

    private static int process(int number) {
        int result = 0;
        log.info("Process: {}", number);
        log.info("Result before: {}", result);
        while (number > 0) {
            result += (number % 10) * (number % 10);
            number = number / 10;
        }
        log.info("Result after: {}", result);
        return result;
    }

    public static String convertDateToBinary(String date) {
        String splitter = "-";
        var words = date.split(splitter);
        java.util.StringJoiner joiner = new java.util.StringJoiner(splitter);
        for (String word : words) {
            joiner.add(toBinary(word));
        }
        return joiner.toString();
    }

    private static String toBinary(String decimalStr) {
        int decimal = Integer.parseInt(decimalStr);
        StringBuilder builder = new StringBuilder();

        while (decimal != 0) {
            builder.insert(0, decimal % 2);
            decimal = decimal / 2;
        }
        return builder.toString();
    }

    private static final Set<Character> vowels = Set.of(
            'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'
    );

    public static boolean isValid(String word) {
        int requiredLength = 3;
        boolean hasConsonant = false;
        boolean hasVowel = false;
        Predicate<Character> isEnglishLetter = ch -> (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
        Predicate<Character> isDigit = ch -> (ch >= '0' && ch <= '9');
        Predicate<Character> isValidSymbol = isEnglishLetter.or(isDigit);
        Predicate<Character> isVowel = vowels::contains;
        Predicate<Character> isConsonant = isEnglishLetter.and(Predicate.not(isVowel));

        if (word.length() < requiredLength) {
            return false;
        }

        for (char ch : word.toCharArray()) {
            if (!isValidSymbol.test(ch)) {
                return false;
            }
            if (!hasConsonant) {
                hasConsonant = isConsonant.test(ch);
            }
            if (!hasVowel) {
                hasVowel = isVowel.test(ch);
            }
        }
        return hasConsonant && hasVowel;
    }

    /**
     * Solution to <a href="https://leetcode.com/problems/reverse-integer/">...</a>
     */
    public static int reverse(int number) {

        int result = 0;
        int digit;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        BiPredicate<Integer, Integer> maxOverflow = (num, lastDigitToAdd) ->
                num > max / 10 || (num == max / 10 && lastDigitToAdd > max % 10);
        BiPredicate<Integer, Integer> minOverflow = (num, lastDigitToAdd) ->
                num < min / 10 || (num == min / 10 && lastDigitToAdd < min % 10);
        while (number != 0) {
            digit = number % 10;

            if (maxOverflow.test(result, digit) || minOverflow.test(result, digit)) {
                return 0;
            }
            result = result * 10 + digit;
            number = number / 10;
        }

        return result;
    }

    /**
     * Solution to <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">...</a>
     * TODO
     */
//    public int lengthOfLongestSubstring(String s) {
//        var existing = new HashMap<Character, Integer>();
//        int maxSubstringLength = -1;
//
//        for (int i = 0; i <= s.length(); i++) {
//            Integer prevIndex = existing.put(s.charAt(i), i);
//            if (prevIndex != null) {
//                maxSubstringLength = Math.max(maxSubstringLength, i - prevIndex);
//            }
//
//            if( i == s.length()) {
//                existing.keySet()
//                        .forEach(k -> {
//                            existing.put()
//                        });
//            }
//        }
//
//        Integer minIndex = existing.values().stream()
//                .min(Integer::compare)
//                .get();
//
//        maxSubstringLength = Math.max(maxSubstringLength, s.length() - minIndex);
//
//        return maxSubstringLength == -1 ? s.length() : maxSubstringLength;
//    }

}
