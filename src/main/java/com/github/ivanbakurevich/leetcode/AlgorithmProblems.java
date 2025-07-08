package com.github.ivanbakurevich.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

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


}
