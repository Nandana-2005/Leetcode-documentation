/*
==========================================================
                KTH MISSING POSITIVE NUMBER
==========================================================

Problem:
Given a sorted array of positive integers arr and an
integer k, return the kth missing positive number.

----------------------------------------------------------
Intuition

At index i,

Expected value = i + 1

If

arr[i] > i + 1

then some numbers are missing before arr[i].

The number of missing elements before index i is

missing = arr[i] - (i + 1)

or

missing = arr[i] - i - 1

Use Binary Search to find the first index where
missing >= k.

----------------------------------------------------------
Approaches

1. Brute Force
2. Binary Search (Optimal)

----------------------------------------------------------
Time Complexities

1. Brute Force
   Time  : O(N + K)
   Space : O(1)

2. Binary Search
   Time  : O(log N)
   Space : O(1)

==========================================================
*/

public class KthMissingPositiveNumber {

    public static void main(String[] args) {

        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;

        System.out.println("Brute Force  : " + findKthPositiveBrute(arr, k));
        System.out.println("Binary Search: " + findKthPositive(arr, k));
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static int findKthPositiveBrute(int[] arr, int k) {

        for (int num : arr) {

            if (num <= k) {

                k++;

            } else {

                break;
            }
        }

        return k;
    }

    // ==========================================================
    // 2. BINARY SEARCH
    // ==========================================================

    public static int findKthPositive(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int missing = arr[mid] - (mid + 1);

            if (missing < k) {

                low = mid + 1;

            } else {

                high = mid - 1;
            }
        }
        //since in some cases high might be at a negative index, and then after calculation we come to the below formula
        //also  written as high + 1 + k
        return low + k;
    }
}
