package week_eleven;

import java.util.Arrays;

/**
 * Problem 1: The Notice Board Shuffle
 * 
 * Category: Sorting Algorithms (Bubble Sort with Early Exit)
 *
 * Task:
 * Sort an array of scores in ascending order by only swapping adjacent elements.
 * Stop as soon as a full sweep makes no swaps.
 * Return {passes, swaps}: the number of sweeps performed and the total number of swaps made.
 *
 * Why an already-sorted board needs only one pass:
 * In an already-sorted array, every adjacent pair satisfies scores[j] <= scores[j+1].
 * During the very first pass (sweep), no elements are out of order, so no swaps are executed.
 * The `swapped` boolean flag remains false at the end of this pass.
 * The early termination check detects `!swapped` and immediately breaks out of the loop,
 * completing the sort in exactly 1 pass and 0 swaps (O(N) best-case time complexity).
 *
 * Complexity Analysis:
 * - Time Complexity:
 *     - Best Case: O(N) when array is already sorted (1 pass of N-1 comparisons).
 *     - Average Case: O(N^2)
 *     - Worst Case: O(N^2) when array is in reverse order.
 * - Extra Space Complexity: O(1) auxiliary space (in-place sorting).
 * - Stability: Stable (adjacent elements with equal values are never swapped since condition is strictly scores[j] > scores[j+1]).
 */
public class NoticeBoardShuffle {

    /**
     * Sorts the array in place and returns statistics: [passes, swaps].
     *
     * @param scores the array of test scores to sort
     * @return int array containing {passes, swaps}
     */
    public static int[] bubbleStats(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return new int[]{scores == null || scores.length == 0 ? 0 : 1, 0};
        }

        int n = scores.length;
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {
        // Test Case 1: Example 1
        int[] scores1 = {5, 1, 4, 2, 8};
        int[] stats1 = bubbleStats(scores1);
        System.out.println("Sorted: " + Arrays.toString(scores1) + " passes = " + stats1[0] + ", swaps = " + stats1[1]);
        // Expected: [1, 2, 4, 5, 8] passes = 3, swaps = 4

        // Test Case 2: Example 2 (Already sorted)
        int[] scores2 = {1, 2, 3, 4};
        int[] stats2 = bubbleStats(scores2);
        System.out.println("Sorted: " + Arrays.toString(scores2) + " passes = " + stats2[0] + ", swaps = " + stats2[1]);
        // Expected: [1, 2, 3, 4] passes = 1, swaps = 0

        // Test Case 3: Reverse sorted
        int[] scores3 = {4, 3, 2, 1};
        int[] stats3 = bubbleStats(scores3);
        System.out.println("Sorted: " + Arrays.toString(scores3) + " passes = " + stats3[0] + ", swaps = " + stats3[1]);
        // Expected: [1, 2, 3, 4] passes = 3, swaps = 6
    }
}