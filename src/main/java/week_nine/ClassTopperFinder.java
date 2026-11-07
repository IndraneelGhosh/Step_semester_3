package week_nine;

/**
 * Problem 1: Class Topper Finder
 *
 * Task:
 * A teacher stores marks as a 2D grid where each row belongs to one student and each
 * column is one subject. Find which student has the highest total marks.
 * If two or more students have the same highest total, return the one with the smallest row index.
 *
 * Complexity:
 * - Time Complexity: O(m * n) where m is number of students and n is number of subjects.
 * - Space Complexity: O(1) additional space beyond the input grid.
 */
public class ClassTopperFinder {

    public static class TopperResult {
        public final int rowIndex;
        public final int total;

        public TopperResult(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    public static TopperResult findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new TopperResult(-1, 0);
        }

        int bestIndex = 0;
        int maxTotal = Integer.MIN_VALUE;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            // Strictly greater ensures tie-break picks smallest row index
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestIndex = i;
            }
        }

        return new TopperResult(bestIndex, maxTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        TopperResult result = findTopper(marks);
        System.out.println("Expected: (1, 259) | Actual: " + result);
    }
}