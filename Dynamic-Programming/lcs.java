/*
==========================================================
            LONGEST COMMON SUBSEQUENCE
                (LeetCode 1143)
==========================================================

Problem:
Given two strings text1 and text2,
return the length of their Longest Common
Subsequence (LCS).

A subsequence is formed by deleting some
(or no) characters without changing the
relative order.

----------------------------------------------------------
Intuition

At every index we have two choices:

If characters match:
    Take both characters.

Else:
    Skip one character from either string.

State:

dp[i][j]

= Length of LCS between

text1[0...i]
and
text2[0...j]

----------------------------------------------------------
Approaches

1. Recursion
2. Memoization
3. Tabulation
4. Space Optimization

----------------------------------------------------------
Time Complexity

Recursion:
O(2^(N+M))

Memoization:
O(N × M)

Tabulation:
O(N × M)

Space Optimization:
O(N × M) time
O(M) space

==========================================================
*/

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String text1 = "abcde";
        String text2 = "ace";

        System.out.println("Recursion         : " + lcsRec(text1, text2));
        System.out.println("Memoization       : " + lcsMemo(text1, text2));
        System.out.println("Tabulation        : " + lcsTab(text1, text2));
        System.out.println("Space Optimization: " + lcsSpace(text1, text2));
    }

    // ==========================================================
    // 1. RECURSION
    // ==========================================================

    public static int lcsRec(String s1, String s2) {

        return solveRec(s1.length() - 1, s2.length() - 1, s1, s2);
    }

    private static int solveRec(int i, int j,
                                String s1,
                                String s2) {

        if (i < 0 || j < 0)
            return 0;

        if (s1.charAt(i) == s2.charAt(j))
            return 1 + solveRec(i - 1, j - 1, s1, s2);

        return Math.max(
                solveRec(i - 1, j, s1, s2),
                solveRec(i, j - 1, s1, s2));
    }

    // ==========================================================
    // 2. MEMOIZATION
    // ==========================================================

    public static int lcsMemo(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n][m];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solveMemo(n - 1, m - 1, s1, s2, dp);
    }

    private static int solveMemo(int i,
                                 int j,
                                 String s1,
                                 String s2,
                                 int[][] dp) {

        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {

            return dp[i][j] =
                    1 + solveMemo(i - 1,
                                  j - 1,
                                  s1,
                                  s2,
                                  dp);
        }

        return dp[i][j] =
                Math.max(
                        solveMemo(i - 1,
                                  j,
                                  s1,
                                  s2,
                                  dp),

                        solveMemo(i,
                                  j - 1,
                                  s1,
                                  s2,
                                  dp));
    }

    // ==========================================================
    // 3. TABULATION
    // ==========================================================

    public static int lcsTab(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];

                } else {

                    dp[i][j] = Math.max(dp[i - 1][j],
                                        dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    // ==========================================================
    // 4. SPACE OPTIMIZATION
    // ==========================================================

    public static int lcsSpace(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[] prev = new int[m + 1];

        for (int i = 1; i <= n; i++) {

            int[] curr = new int[m + 1];

            for (int j = 1; j <= m; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    curr[j] = 1 + prev[j - 1];

                } else {

                    curr[j] = Math.max(prev[j],
                                       curr[j - 1]);
                }
            }

            prev = curr;
        }

        return prev[m];
    }
}
