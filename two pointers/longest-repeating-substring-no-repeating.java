/*
==========================================================
      LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
==========================================================

Problem:
Given a string s, find the length of the longest
substring without repeating characters.

A substring is a contiguous sequence of characters.

----------------------------------------------------------
Intuition

Use the Sliding Window technique.

Maintain a window [left, right] such that all characters
inside the window are unique.

For every character:

1. If it has already appeared inside the current window,
   move the left pointer to one position after its last
   occurrence.

2. Update the last occurrence of the current character.

3. Update the maximum window length.

----------------------------------------------------------
Approaches

1. Brute Force
2. Sliding Window using HashSet
3. Sliding Window using Last Occurrence Array (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N²)
   Space : O(1)

2. Sliding Window (HashSet)
   Time  : O(2N)
   Space : O(256)

3. Sliding Window (Last Occurrence Array)
   Time  : O(N)
   Space : O(256)

==========================================================
*/

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {

        String s = "abcabcbb";

        System.out.println("Brute Force      : " + lengthBrute(s));
        System.out.println("Sliding Window   : " + lengthHashSet(s));
        System.out.println("Optimal          : " + lengthOptimal(s));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static int lengthBrute(String s) {

        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {

            boolean[] visited = new boolean[256];

            for (int j = i; j < s.length(); j++) {

                char ch = s.charAt(j);

                if (visited[ch])
                    break;

                visited[ch] = true;

                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }

    // ==========================================================
    // 2. SLIDING WINDOW (HASHSET)
    // ==========================================================

    public static int lengthHashSet(String s) {

        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            while (set.contains(s.charAt(right))) {

                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // ==========================================================
    // 3. OPTIMAL (LAST OCCURRENCE ARRAY)
    // ==========================================================

    public static int lengthOptimal(String s) {

        int[] lastIndex = new int[256];

        Arrays.fill(lastIndex, -1);

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Character already exists inside current window
            if (lastIndex[ch] >= left)
                left = lastIndex[ch] + 1;

            // Store latest occurrence
            lastIndex[ch] = right;

            // Update maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
