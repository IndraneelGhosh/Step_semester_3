package week_nine;

/**
 * Problem 4: Hot Weather Alert Windows
 *
 * Task:
 * A weather station records temperature readings. An alert is raised for every block of
 * k consecutive hours whose average temperature is >= threshold.
 * Count the number of qualifying blocks using a sliding window.
 *
 * Complexity:
 * - Brute Force Recalculation: O((N - k + 1) * k) time.
 * - Sliding Window Solution: O(N) time (initial window O(k), then N-k slides at O(1) each).
 *   Space Complexity: O(1) auxiliary space.
 */
public class HotWeatherAlertWindows {

    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        // Compare sum >= k * threshold to avoid decimal division inaccuracies
        long targetSum = (long) k * threshold;
        long currentWindowSum = 0;

        // Compute sum of the first window of size k
        for (int i = 0; i < k; i++) {
            currentWindowSum += readings[i];
        }

        int alertCount = 0;
        if (currentWindowSum >= targetSum) {
            alertCount++;
        }

        // Slide the window across the remaining readings
        for (int i = k; i < readings.length; i++) {
            currentWindowSum += readings[i] - readings[i - k];
            if (currentWindowSum >= targetSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        int alerts = countAlerts(readings, k, threshold);
        System.out.println("Expected Output: 3 | Actual: " + alerts);
    }
}