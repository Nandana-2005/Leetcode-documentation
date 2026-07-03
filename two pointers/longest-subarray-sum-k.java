/*
==========================================================
          LONGEST SUBARRAY WITH SUM EQUAL TO K
==========================================================

Problem:
Given an integer array nums and an integer K,
find the length of the longest subarray whose sum
is equal to K.

----------------------------------------------------------
Intuition

Maintain a running prefix sum.

If

prefixSum - K

has already been seen, then the subarray between
(previousIndex + 1) and currentIndex has sum = K.

Store only the FIRST occurrence of every prefix sum
because it gives the longest possible subarray.

----------------------------------------------------------
Approaches

1. Brute Force - if numbers are positive, -ve or 0
2. Prefix Sum + HashMap (Optimal) if numbers are positive, negative, or 0
3. Two-pointer approach if all numbers are only positive

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N²)
   Space : O(1)

2. Prefix Sum + HashMap
   Time  : O(N)
   Space : O(N)

==========================================================
*/

import java.util.*;

public class LongestSubarraySumEqualsK {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        int k = 6;

        System.out.println("Brute Force : " + longestSubarrayBrute(nums, k));
        System.out.println("Optimal     : " + longestSubarrayOptimal(nums, k));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static int longestSubarrayBrute(int[] nums, int k) {

        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {

            int sum = 0;

            for (int j = i; j < nums.length; j++) {

                sum += nums[j];

                if (sum == k)
                    maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }

    // ==========================================================
    // 2. PREFIX SUM + HASHMAP (OPTIMAL)
    // ==========================================================

    public static int longestSubarrayOptimal(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {

            prefixSum += nums[i];

            // Entire array from 0...i has sum = k
            if (prefixSum == k)
                maxLength = i + 1;

            int remaining = prefixSum - k;

            // Found a previous prefix sum - map.get(remaining) gives the ending index of the remaining sum eqauting subarray
            if (map.containsKey(remaining))
                maxLength = Math.max(maxLength,
                        i - map.get(remaining));

            // Store only the first occurrence
            if (!map.containsKey(prefixSum))
                map.put(prefixSum, i);
        }

        return maxLength;
    }
}
