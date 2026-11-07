package week_nine;

/**
 * Problem 5: Ticket Price Slot Finder
 *
 * Task:
 * Given a sorted list of distinct ticket prices and a newPrice, find the index at which
 * the price already exists, or the index where it should be inserted to stay sorted.
 *
 * Complexity:
 * - Linear Scan: O(N) time, O(1) space.
 * - Binary Search: O(log N) time, O(1) auxiliary space.
 */
public class TicketPriceSlotFinder {

    public static int findSlot(int[] prices, int newPrice) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid; // Exact match found
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // When not found, low points to the exact insertion slot
        return low;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        System.out.println("Sample 1 (newPrice = 150): Expected 1 | Actual: " + findSlot(prices, 150));
        System.out.println("Sample 2 (newPrice = 210): Expected 3 | Actual: " + findSlot(prices, 210));
        System.out.println("Sample 3 (newPrice = 300): Expected 4 | Actual: " + findSlot(prices, 300));
    }
}