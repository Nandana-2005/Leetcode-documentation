/*
==========================================================
        MINIMUM TIME REQUIRED (HackerRank)
==========================================================

Problem:
Given an array machines[], where machines[i] represents
the number of days required by the ith machine to produce
one item, and a production goal.

Return the minimum number of days required to produce
at least the goal number of items.

----------------------------------------------------------
Intuition

We are NOT searching for a machine.

We are searching for the MINIMUM NUMBER OF DAYS.

For every possible number of days,

Can all machines together produce at least
'goal' items?

If YES
    Try fewer days.

If NO
    Try more days.

Hence,

Binary Search on Answer.

----------------------------------------------------------
Approaches

1. Brute Force
2. Binary Search on Answer (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(high × N)

2. Binary Search
   Time  : O(N log(high))

Space

O(1)

where

high = fastestMachine × goal

==========================================================
*/

import java.util.*;

public class MinimumTimeRequired {

    public static void main(String[] args) {

        long[] machines = {2, 3, 2};
        long goal = 10;

        System.out.println("Brute Force  : " + minTimeBrute(machines, goal));
        System.out.println("Binary Search: " + minTime(machines, goal));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static long minTimeBrute(long[] machines, long goal) {

        long fastest = Long.MAX_VALUE;

        for (long machine : machines)
            fastest = Math.min(fastest, machine);

        long high = fastest * goal;

        for (long days = 1; days <= high; days++) {

            if (canProduce(machines, goal, days))
                return days;
        }

        return -1;
    }

    // ==========================================================
    // 2. BINARY SEARCH
    // ==========================================================

    public static long minTime(long[] machines, long goal) {

        long fastest = Long.MAX_VALUE;

        for (long machine : machines)
            fastest = Math.min(fastest, machine);

        long low = 1;

        // Upper bound:
        // If only the fastest machine works,
        // it needs fastest * goal days.
        long high = fastest * goal;

        long ans = high;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            if (canProduce(machines, goal, mid)) {

                ans = mid;

                // Try fewer days
                high = mid - 1;

            } else {

                // Need more days
                low = mid + 1;
            }
        }

        return ans;
    }

    // ==========================================================
    // CHECK IF GOAL CAN BE ACHIEVED
    // ==========================================================

    private static boolean canProduce(long[] machines,
                                      long goal,
                                      long days) {

        long products = 0;

        for (long machine : machines) {
            //calculates products produced for a particular day
            //'machine' days gives 1 item then how many items does 'days' day produce is the formula used below 
            products += days / machine;

            // Prevent unnecessary computation
            if (products >= goal)
                return true;
        }

        return false;
    }
}
