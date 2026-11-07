package week_eleven;

import java.util.Arrays;

/**
 * Problem 3: The Attendance Disorder Score
 *
 * Category: Sorting Algorithms (Merge Sort / Inversion Counting)
 *
 * Task:
 * Students are supposed to enter the lab in roll-number order.
 * The lab assistant measures how disordered the actual entry sequence was by
 * counting disorder pairs: pairs (i, j) with i < j but roll[i] > roll[j].
 * Implement long countInversions(int[] roll) that returns this count.
 * Checking every pair is O(n^2); your solution must run in O(n log n)
 * by counting the pairs while you split and merge the array.
 *
 * Hint:
 * During the merge step, whenever an element from the right half is copied
 * before the remaining elements of the left half, it forms a disorder pair
 * with every one of them: add (mid - i + 1) to the count.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N) across all cases (best, average, worst) due to the divide-and-conquer recurrence T(N) = 2T(N/2) + O(N).
 * - Extra Space Complexity: O(N) auxiliary space for the temporary merge buffer.
 * - Stability: Stable. When roll[i] == roll[j], the left element roll[i] is taken first, so no inversion is counted and equal elements preserve relative order.
 */
public class AttendanceDisorderScore {

    /**
     * Counts the total number of inversion pairs in O(N log N) time without modifying the original input array.
     *
     * @param roll the input array of roll numbers
     * @return the total disorder score (number of inversions)
     */
    public static long countInversions(int[] roll) {
        if (roll == null || roll.length <= 1) {
            return 0;
        }

        int[] arr = roll.clone();
        int[] temp = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSortAndCount(arr, temp, left, mid);
            count += mergeSortAndCount(arr, temp, mid + 1, right);
            count += mergeAndCount(arr, temp, left, mid, right);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;     // Index for left subarray [left..mid]
        int j = mid + 1;  // Index for right subarray [mid+1..right]
        int k = left;     // Index for merged result in temp buffer
        long count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                // arr[i] > arr[j] means arr[j] is smaller than all remaining elements in the left half: arr[i..mid]
                temp[k++] = arr[j++];
                count += (mid - i + 1);
            }
        }

        // Copy remaining elements of left subarray, if any
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements of right subarray, if any
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy merged subarray back to original array
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return count;
    }

    public static void main(String[] args) {
        // Test Case 1: Example 1
        int[] roll1 = {2, 4, 1, 3, 5};
        long score1 = countInversions(roll1);
        System.out.println("Input: " + Arrays.toString(roll1) + " -> Inversion count: " + score1);
        // Expected: 3 (disorder pairs: (2,1), (4,1), (4,3))

        // Test Case 2: Example 2 (Strictly decreasing)
        int[] roll2 = {5, 4, 3, 2, 1};
        long score2 = countInversions(roll2);
        System.out.println("Input: " + Arrays.toString(roll2) + " -> Inversion count: " + score2);
        // Expected: 10 (all 5*4/2 = 10 pairs)

        // Test Case 3: Already sorted
        int[] roll3 = {1, 2, 3, 4, 5};
        long score3 = countInversions(roll3);
        System.out.println("Input: " + Arrays.toString(roll3) + " -> Inversion count: " + score3);
        // Expected: 0
    }
}