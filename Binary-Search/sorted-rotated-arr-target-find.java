/*
==========================================================
        SEARCH IN ROTATED SORTED ARRAY - I
==========================================================

Problem:
Given a rotated sorted array containing DISTINCT
elements, return the index of the target.
If not found, return -1.

----------------------------------------------------------
Intuition

At every step, one half of the array is always sorted.

1. Check whether the LEFT half is sorted.
2. If yes, determine whether the target lies inside it.
3. Otherwise, search in the RIGHT half.

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

public class SearchRotatedSortedArrayI {

    public static void main(String[] args) {

        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        System.out.println("Linear Search : " + linearSearch(nums, target));
        System.out.println("Binary Search : " + search(nums, target));
    }

    // ==========================================================
    // 1. LINEAR SEARCH
    // ==========================================================

    public static int linearSearch(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == target)
                return i;
        }

        return -1;
    }

    // ==========================================================
    // 2. BINARY SEARCH
    // ==========================================================

    public static int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target)
                return mid;

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                //checking if in the sorted part the target could exist by checking if target is between low and mid
                if (target >= nums[low] && target < nums[mid]) {

                    high = mid - 1;

                } else {

                    low = mid + 1;
                }
            }

            // Right half is sorted
            else {
                //checking if in the sorted part the target could exist by checking if target is between mid and high
                if (target > nums[mid] && target <= nums[high]) {

                    low = mid + 1;

                } else {

                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}













/*
==========================================================
        SEARCH IN ROTATED SORTED ARRAY - II
==========================================================

Problem:
Given a rotated sorted array that MAY CONTAIN DUPLICATES,
return true if target exists, otherwise false.

----------------------------------------------------------
Intuition

Same idea as Problem I.

The only additional case:

nums[low] == nums[mid] == nums[high]

Here we cannot determine which side is sorted.

So shrink both ends.

----------------------------------------------------------
Approaches

1. Linear Search
2. Modified Binary Search

----------------------------------------------------------
Time Complexities

1. Linear Search
   Time  : O(N)
   Space : O(1)

2. Modified Binary Search
   Average : O(log N)
   Worst   : O(N)
   Space   : O(1)

==========================================================
*/

public class SearchRotatedSortedArrayII {

    public static void main(String[] args) {

        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        int target = 0;

        System.out.println("Linear Search : " + linearSearch(nums, target));
        System.out.println("Binary Search : " + search(nums, target));
    }

    // ==========================================================
    // 1. LINEAR SEARCH
    // ==========================================================

    public static boolean linearSearch(int[] nums, int target) {

        for (int num : nums) {

            if (num == target)
                return true;
        }

        return false;
    }

    // ==========================================================
    // 2. MODIFIED BINARY SEARCH
    // ==========================================================

    public static boolean search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target)
                return true;

            // Duplicate case
            if (nums[low] == nums[mid] &&
                nums[mid] == nums[high]) {

                low++;
                high--;

                continue;
            }

            // Left half is sorted
            if (nums[low] <= nums[mid]) {

                if (target >= nums[low] &&
                    target < nums[mid]) {

                    high = mid - 1;

                } else {

                    low = mid + 1;
                }
            }

            // Right half is sorted
            else {

                if (target > nums[mid] &&
                    target <= nums[high]) {

                    low = mid + 1;

                } else {

                    high = mid - 1;
                }
            }
        }

        return false;
    }
}
