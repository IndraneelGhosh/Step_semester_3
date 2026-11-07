package week_nine;

import java.util.Arrays;

/**
 * Problem 2: Merging Two Token Queues
 *
 * Task:
 * Combine two ascending sorted lists of token numbers into a single sorted list,
 * keeping duplicates. Use the two-pointer technique; do not concatenate and sort.
 *
 * Complexity:
 * - Two-pointer Time Complexity: O(m + n) single linear pass.
 * - Space Complexity: O(m + n) for the output list, O(1) auxiliary space.
 * - Comparison: Concatenating and sorting takes O((m + n) log(m + n)) time, which is strictly
 *   slower than the O(m + n) two-pointer merge.
 */
public class MergeTokenQueues {

    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        if (counterA == null) counterA = new int[0];
        if (counterB == null) counterB = new int[0];

        int m = counterA.length;
        int n = counterB.length;
        int[] merged = new int[m + n];

        int i = 0; // Pointer for counterA
        int j = 0; // Pointer for counterB
        int k = 0; // Pointer for merged

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        // Copy remaining elements of counterA
        while (i < m) {
            merged[k++] = counterA[i++];
        }

        // Copy remaining elements of counterB
        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        // Sample 1
        int[] a1 = {3, 8, 15, 20};
        int[] b1 = {5, 8, 12};
        System.out.println("Sample 1 Output: " + Arrays.toString(mergeTokens(a1, b1)));

        // Sample 2
        int[] a2 = {};
        int[] b2 = {4, 9};
        System.out.println("Sample 2 Output: " + Arrays.toString(mergeTokens(a2, b2)));
    }
}