/*
==========================================================
                    WORD BREAK I
==========================================================

Problem:
Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated
sequence of one or more dictionary words.

A dictionary word may be reused multiple times.

----------------------------------------------------------
Intuition

At every index, try every possible substring starting
from that index.

If the substring exists in the dictionary, recursively
check the remaining part of the string.

If any partition leads to the end of the string,
return true.

----------------------------------------------------------
Approaches

1. Recursion
2. Memoization
3. Tabulation

----------------------------------------------------------
Time Complexities

1. Recursion
   Time : Exponential
   Space : O(N)

2. Memoization
   Time  : O(N²)
   Space : O(N) + O(N)

3. Tabulation
   Time  : O(N²)
   Space : O(N)

==========================================================
*/

import java.util.*;

public class WordBreak {

    public static void main(String[] args) {

        String s = "leetcode";

        List<String> wordDict = Arrays.asList("leet", "code");

        System.out.println("Recursion   : " + wordBreakRecursive(s, wordDict));
        System.out.println("Memoization : " + wordBreakMemo(s, wordDict));
        System.out.println("Tabulation  : " + wordBreakTab(s, wordDict));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static boolean wordBreakRecursive(String s,
                                             List<String> wordDict) {

        HashSet<String> set = new HashSet<>(wordDict);

        return solve(0, s, set);
    }

    private static boolean solve(int index,
                                 String s,
                                 HashSet<String> set) {

        // Reached the end
        if (index == s.length())
            return true;

        for (int end = index + 1; end <= s.length(); end++) {

            String word = s.substring(index, end);

            if (set.contains(word)) {

                if (solve(end, s, set))
                    return true;
            }
        }

        return false;
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static boolean wordBreakMemo(String s,
                                        List<String> wordDict) {

        HashSet<String> set = new HashSet<>(wordDict);

        int[] dp = new int[s.length()];

        Arrays.fill(dp, -1);

        return solveMemo(0, s, set, dp);
    }

    private static boolean solveMemo(int index,
                                     String s,
                                     HashSet<String> set,
                                     int[] dp) {

        if (index == s.length())
            return true;

        if (dp[index] != -1)
            return dp[index] == 1;

        for (int end = index + 1; end <= s.length(); end++) {

            String word = s.substring(index, end);

            if (set.contains(word)) {

                if (solveMemo(end, s, set, dp)) {

                    dp[index] = 1;
                    return true;
                }
            }
        }

        dp[index] = 0;

        return false;
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static boolean wordBreakTab(String s,
                                       List<String> wordDict) {

        HashSet<String> set = new HashSet<>(wordDict);

        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        //dp[i] means can the substring[i....n-1] be segmented
        // Empty string can always be segmented
        dp[n] = true;

        for (int index = n - 1; index >= 0; index--) {

            for (int end = index + 1; end <= n; end++) {

                String word = s.substring(index, end);

                if (set.contains(word) && dp[end]) {

                    dp[index] = true;
                    break;
                }
            }
        }

        return dp[0];
    }
}
