/*
==========================================================
      FIRST, LAST AND COUNT OF OCCURRENCES
==========================================================

Problem:
Given a sorted array and a target element,

Find:
1. First Occurrence
2. Last Occurrence
3. Total Count of Occurrences

----------------------------------------------------------
Intuition

Use Binary Search twice.

1. First Occurrence
   - If target is found, continue searching on the left.

2. Last Occurrence
   - If target is found, continue searching on the right.

3. Count
   = Last Occurrence - First Occurrence + 1

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

public class FirstLastCountOccurrences {

    public static void main(String[] args) {

        int[] nums = {2, 4, 4, 4, 6, 8, 8, 10};
        int target = 4;

        int first = firstOccurrence(nums, target);
        int last = lastOccurrence(nums, target);
        int count = countOccurrences(nums, target);

        System.out.println("First Occurrence : " + first);
        System.out.println("Last Occurrence  : " + last);
        System.out.println("Count            : " + count);
    }

    // ==========================================================
    // 1. LINEAR SEARCH
    // ==========================================================

    public static int firstOccurrenceLinear(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == target)
                return i;
        }

        return -1;
    }

    public static int lastOccurrenceLinear(int[] nums, int target) {

        for (int i = nums.length - 1; i >= 0; i--) {

            if (nums[i] == target)
                return i;
        }

        return -1;
    }

    // ==========================================================
    // 2. BINARY SEARCH (FIRST OCCURRENCE)
    // ==========================================================

    public static int firstOccurrence(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                answer = mid;

                // Search on left side since the first occurance would be on left side only
                right = mid - 1;

            } else if (nums[mid] < target) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }

    // ==========================================================
    // 3. BINARY SEARCH (LAST OCCURRENCE)
    // ==========================================================

    public static int lastOccurrence(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                answer = mid;

                // Search on right side since the first occurrence would be on  right side only
                left = mid + 1;

            } else if (nums[mid] < target) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }

    // ==========================================================
    // 4. COUNT OCCURRENCES
    // ==========================================================

    public static int countOccurrences(int[] nums, int target) {

        int first = firstOccurrence(nums, target);

        if (first == -1)
            return 0;

        int last = lastOccurrence(nums, target);

        return last - first + 1;
    }
}
