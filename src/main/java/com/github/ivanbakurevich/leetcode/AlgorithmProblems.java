package com.github.ivanbakurevich.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;

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
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        long rightSum = sum;
        long leftSum = 0L;

        for(int i=0;i<nums.length;i++) {
            rightSum -= nums[i];
            if(leftSum == rightSum) {
                return i;
            }
            leftSum+=nums[i];
        }

        return -1;
    }

    /**
     * Solution to <a href="https://leetcode.com/problems/longest-common-prefix/">...</a>
     */
    public static String longestCommonPrefix(String[] strs) {
        String currentString = strs[0];
        for(int i=1;i<strs.length;i++) {
            currentString = findCommon(currentString, strs[i]);
            if(currentString.isEmpty()){
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
