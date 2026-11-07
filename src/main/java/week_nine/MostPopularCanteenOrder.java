package week_nine;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 3: Most Popular Canteen Order
 *
 * Task:
 * Given a list of order item names, find the most frequently ordered item.
 * If two or more items have the same highest count, return the one that appears
 * first in the orders list.
 *
 * Complexity:
 * - Hash Map Solution: O(N) time (one pass to count frequencies, second pass to pick first with max count).
 *   Space Complexity: O(U) where U is the number of unique items.
 * - Brute Force (rescan for each item): O(N^2) time, O(1) extra space.
 */
public class MostPopularCanteenOrder {

    public static class PopularItemResult {
        public final String item;
        public final int count;

        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    public static PopularItemResult mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            return null;
        }

        // Pass 1: Build frequency map
        Map<String, Integer> freqMap = new HashMap<>();
        int maxCount = 0;

        for (String order : orders) {
            int newCount = freqMap.getOrDefault(order, 0) + 1;
            freqMap.put(order, newCount);
            if (newCount > maxCount) {
                maxCount = newCount;
            }
        }

        // Pass 2: Iterate orders in original sequence to break ties by first appearance
        for (String order : orders) {
            if (freqMap.get(order) == maxCount) {
                return new PopularItemResult(order, maxCount);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Sample 1
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println("Sample 1 Output: " + mostPopular(orders1));

        // Sample 2 (tie-break test)
        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println("Sample 2 Output: " + mostPopular(orders2));
    }
}