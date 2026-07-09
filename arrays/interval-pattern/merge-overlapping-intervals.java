/*
==========================================================
                MERGE OVERLAPPING INTERVALS
==========================================================

Problem:
Given an array of intervals where intervals[i] =
[start, end], merge all overlapping intervals and
return an array of the non-overlapping intervals.

----------------------------------------------------------
Intuition

1. Sort the intervals based on their starting time.
2. Start with the first interval.
3. Compare the current interval with the last merged interval.
4. If they overlap, merge them by updating the end time.
5. Otherwise, add the current interval as a new interval.

----------------------------------------------------------
Approaches

1. Brute Force
2. Greedy (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N²)
   Space : O(N)

2. Greedy
   Time  : O(N log N)
   Space : O(N)

==========================================================
*/

import java.util.*;

public class MergeOverlappingIntervals {

    public static void main(String[] args) {

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        int[][] answer = mergeOptimal(intervals);

        System.out.println("Merged Intervals:");

        for (int[] interval : answer)
            System.out.println(Arrays.toString(interval));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static int[][] mergeBrute(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> answer = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {

            int start = intervals[i][0];
            int end = intervals[i][1];

            // Already merged
            if (!answer.isEmpty() &&
                    end <= answer.get(answer.size() - 1)[1])
                continue;

            for (int j = i + 1; j < intervals.length; j++) {

                if (intervals[j][0] <= end) {

                    end = Math.max(end, intervals[j][1]);

                } else {

                    break;
                }
            }

            answer.add(new int[]{start, end});
        }

        return answer.toArray(new int[answer.size()][]);
    }

    // ==========================================================
    // 2. GREEDY (OPTIMAL)
    // ==========================================================

    public static int[][] mergeOptimal(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> answer = new ArrayList<>();

        for (int[] interval : intervals) {

            // No overlap 
            //when answer array is empty or when s2 > e1 then add current interval
            if (answer.isEmpty() ||
                    interval[0] > answer.get(answer.size() - 1)[1]) {

                answer.add(interval);

            }

            // Overlap
            else {
                //max of the end times are placed in answer array
                answer.get(answer.size() - 1)[1] =
                        Math.max(answer.get(answer.size() - 1)[1],
                                interval[1]);
            }
        }

        return answer.toArray(new int[answer.size()][]);
    }
}
