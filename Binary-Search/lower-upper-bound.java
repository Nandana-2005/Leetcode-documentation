/*
==========================================================
            LOWER BOUND & UPPER BOUND
==========================================================

Problem:
Given a sorted array and a target, find

1. Lower Bound
2. Upper Bound

----------------------------------------------------------
Definitions

Lower Bound:
The first index such that
arr[index] >= target

Upper Bound:
The first index such that
arr[index] > target

If no such index exists,
return arr.length.

----------------------------------------------------------
Where are Lower & Upper Bound Useful?

1. Search Insert Position
   Answer = Lower Bound

2. First Occurrence
   first = Lower Bound(target)

3. Last Occurrence
   last = Upper Bound(target) - 1

4. Count Occurrences
   count = Upper Bound(target) - Lower Bound(target)

5. Floor and Ceil

   Floor = Lower Bound(target) - 1
   Ceil  = Lower Bound(target)

6. Find Position to Insert in Sorted Array

   Insert Position = Lower Bound(target)

7. Number of Elements Smaller Than Target

   Lower Bound(target)

8. Number of Elements Less Than or Equal to Target

   Upper Bound(target)

----------------------------------------------------------
Approaches

1. Linear Search
2. Binary Search (Optimal)

----------------------------------------------------------
Time Complexities

1. Linear Search
   Time  : O(N)
   Space : O(1)

2. Binary Search
   Time  : O(log N)
   Space : O(1)

==========================================================
*/

public class LowerUpperBound {

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 2, 4, 5, 7};
        int target = 2;

        System.out.println("Lower Bound : " + lowerBound(arr, target));
        System.out.println("Upper Bound : " + upperBound(arr, target));

        System.out.println("First Occurrence : " + firstOccurrence(arr, target));
        System.out.println("Last Occurrence  : " + lastOccurrence(arr, target));
        System.out.println("Count            : " + countOccurrences(arr, target));
    }

    // ==========================================================
    // 1. LINEAR SEARCH
    // ==========================================================

    public static int lowerBoundLinear(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] >= target)
                return i;
        }

        return arr.length;
    }

    public static int upperBoundLinear(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > target)
                return i;
        }

        return arr.length;
    }

    // ==========================================================
    // 2. LOWER BOUND (First element >= target)
    // ==========================================================

    public static int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        int answer = arr.length;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {

                answer = mid;

                // Look for a better answer on the left
                right = mid - 1;

            } else {

                left = mid + 1;
            }
        }

        return answer;
    }

    // ==========================================================
    // 3. UPPER BOUND (First element > target)
    // ==========================================================

    public static int upperBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        int answer = arr.length;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] > target) {

                answer = mid;

                // Look for a better answer on the left
                right = mid - 1;

            } else {

                left = mid + 1;
            }
        }

        return answer;
    }

    // ==========================================================
    // APPLICATIONS
    // ==========================================================

    // First Occurrence
    public static int firstOccurrence(int[] arr, int target) {

        int lb = lowerBound(arr, target);

        if (lb == arr.length || arr[lb] != target)
            return -1;

        return lb;
    }

    // Last Occurrence
    public static int lastOccurrence(int[] arr, int target) {

        int ub = upperBound(arr, target);

        if (ub == 0 || arr[ub - 1] != target)
            return -1;

        return ub - 1;
    }

    // Count Occurrences
    public static int countOccurrences(int[] arr, int target) {

        int lb = lowerBound(arr, target);
        int ub = upperBound(arr, target);

        return ub - lb;
    }
}
