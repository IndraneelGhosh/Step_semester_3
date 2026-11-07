package week_eleven;

import java.util.Arrays;

/**
 * Problem 5: The Contest Leaderboard
 *
 * Category: Sorting Algorithms (Counting Sort - Descending & Stable)
 *
 * Task:
 * After a live coding contest, each Entry has a student name and marks between 0 and 100.
 * The leaderboard must list students from highest marks to lowest, and students with equal marks
 * must stay in the order in which they submitted (the input order).
 * Implement Entry[] leaderboard(Entry[] entries) using counting on the marks values,
 * without comparing two entries with each other, so that it runs in O(n + 101) time.
 *
 * Hint:
 * Use key = 100 - marks so that higher marks get smaller keys (0 to 100).
 * Build cumulative counts, then place entries into the output by walking the input
 * from right to left to keep ties in submission order.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N + K) where K = 101 (possible marks from 0 to 100). Total time is O(N + 101) = O(N).
 * - Extra Space Complexity: O(N + K) = O(N + 101) for the count array of size 101 and output array of size N.
 * - Stability: Stable. Walking the original input array backwards (from right to left) and placing each element
 *   at `count[key] - 1` while decrementing `count[key]` guarantees that elements with the same key maintain their
 *   original relative submission order.
 */
public class ContestLeaderboard {

    public static class Entry {
        private final String name;
        private final int marks;

        public Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }

        @Override
        public String toString() {
            return name + " " + marks;
        }
    }

    /**
     * Produces a stable leaderboard sorted from highest marks to lowest in O(N + 101) time.
     *
     * @param entries array of contest entries
     * @return new array sorted by marks in descending order, with ties preserved in submission order
     */
    public static Entry[] leaderboard(Entry[] entries) {
        if (entries == null || entries.length <= 1) {
            return entries == null ? new Entry[0] : entries.clone();
        }

        int n = entries.length;
        int k = 101; // marks from 0 to 100
        int[] count = new int[k];

        // Step 1: Count frequency of transformed keys: key = 100 - marks
        // Highest marks (100) -> key 0; Lowest marks (0) -> key 100
        for (Entry entry : entries) {
            int key = 100 - entry.getMarks();
            count[key]++;
        }

        // Step 2: Compute prefix sums (cumulative counts)
        for (int i = 1; i < k; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build output array walking right to left for stability
        Entry[] output = new Entry[n];
        for (int i = n - 1; i >= 0; i--) {
            int key = 100 - entries[i].getMarks();
            output[count[key] - 1] = entries[i];
            count[key]--;
        }

        return output;
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        System.out.println("Input:    " + Arrays.toString(entries));
        Entry[] sorted = leaderboard(entries);
        System.out.println("Output:   " + Arrays.toString(sorted));
        // Expected Output: Kiran 100, Asha 90, Neha 90, Ravi 75, Meera 75
    }
}