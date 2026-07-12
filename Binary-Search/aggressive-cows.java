/*
==========================================================
                AGGRESSIVE COWS
==========================================================

Problem:
Given the positions of N stalls and K cows,
place all the cows in the stalls such that the
minimum distance between any two cows is maximized.

Return the largest possible minimum distance.

----------------------------------------------------------
Intuition

The answer is not a stall position.

The answer is the MINIMUM DISTANCE between cows.

Binary Search on the Answer.

For every possible minimum distance,

Can we place all K cows while maintaining
at least this minimum distance?

If YES
    Try a larger distance.

If NO
    Try a smaller distance.

----------------------------------------------------------
Approaches

1. Brute Force
2. Binary Search on Answer (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N × D)

2. Binary Search
   Time  : O(N log D)

Space

O(1)

where

D = maxPosition - minPosition

==========================================================
*/

import java.util.Arrays;

public class AggressiveCows {

    public static void main(String[] args) {

        int[] stalls = {1, 2, 4, 8, 9};
        int cows = 3;

        System.out.println("Brute Force  : " + aggressiveCowsBrute(stalls, cows));
        System.out.println("Binary Search: " + aggressiveCows(stalls, cows));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static int aggressiveCowsBrute(int[] stalls, int cows) {

        Arrays.sort(stalls);

        int limit = stalls[stalls.length - 1] - stalls[0];

        for (int distance = 1; distance <= limit; distance++) {

            if (!canPlace(stalls, cows, distance))
                return distance - 1;
        }

        return limit;
    }

    // ==========================================================
    // 2. BINARY SEARCH
    // ==========================================================

    public static int aggressiveCows(int[] stalls, int cows) {

        Arrays.sort(stalls);

        int low = 1;

        int high = stalls[stalls.length - 1] - stalls[0];

        int ans = 0;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canPlace(stalls, cows, mid)) {

                ans = mid;

                // Try a larger minimum distance
                low = mid + 1;

            } else {

                high = mid - 1;
            }
        }

        return ans;
    }

    // ==========================================================
    // CHECK IF WE CAN PLACE ALL COWS
    // ==========================================================

    private static boolean canPlace(int[] stalls,
                                    int cows,
                                    int minDistance) {

        int count = 1;

        int lastPlaced = stalls[0];

        for (int i = 1; i < stalls.length; i++) {

            if (stalls[i] - lastPlaced >= minDistance) {

                count++;

                lastPlaced = stalls[i];
            }

            if (count >= cows)
                return true;
        }

        return false;
    }
}
