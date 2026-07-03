/*
==========================================================
            FIND ALL ANAGRAMS IN A STRING
==========================================================

Problem:
Given two strings s and p, return all the starting
indices of p's anagrams in s.

----------------------------------------------------------
Intuition

Use a Fixed Size Sliding Window.

1. Store the frequency of characters in p.
2. Expand the window until its size becomes p.length().
3. Compare the frequency arrays.
4. If they are equal, the current window is an anagram.
5. Slide the window by removing the left character and
   adding the next right character.

----------------------------------------------------------
Approaches

1. Brute Force (Sort Every Window)
2. Sliding Window with Frequency Arrays (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O((N-M+1) × M log M)
   Space : O(M)

2. Sliding Window
   Time  : O(N)
   Space : O(26)

where

N = length of s
M = length of p

==========================================================
*/

import java.util.*;

public class FindAllAnagramsInString {

    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";

        System.out.println("Brute Force : " + findAnagramsBrute(s, p));
        System.out.println("Optimal     : " + findAnagramsOptimal(s, p));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static List<Integer> findAnagramsBrute(String s, String p) {

        List<Integer> answer = new ArrayList<>();

        char[] target = p.toCharArray();
        Arrays.sort(target);

        int windowSize = p.length();

        for (int i = 0; i <= s.length() - windowSize; i++) {

            char[] current = s.substring(i, i + windowSize).toCharArray();

            Arrays.sort(current);

            if (Arrays.equals(target, current))
                answer.add(i);
        }

        return answer;
    }

    // ==========================================================
    // 2. SLIDING WINDOW (OPTIMAL)
    // ==========================================================

    public static List<Integer> findAnagramsOptimal(String s, String p) {

        List<Integer> answer = new ArrayList<>();

        if (p.length() > s.length())
            return answer;

        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];

        // Frequency of pattern
        for (char ch : p.toCharArray())
            pFreq[ch - 'a']++;

        int windowSize = p.length();

        // Build first window
        for (int i = 0; i < windowSize; i++)
            windowFreq[s.charAt(i) - 'a']++;

        // Check first window
        if (Arrays.equals(pFreq, windowFreq))
            answer.add(0);

        // Slide the window
        for (int right = windowSize; right < s.length(); right++) {

            // Remove left character
            windowFreq[s.charAt(right - windowSize) - 'a']--;

            // Add new right character
            windowFreq[s.charAt(right) - 'a']++;

            if (Arrays.equals(pFreq, windowFreq))
                answer.add(right - windowSize + 1);
        }

        return answer;
    }
}
