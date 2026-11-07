package week_eleven;

import java.util.Arrays;

/**
 * Problem 2: The Hackathon Registration Desk
 *
 * Category: Sorting Algorithms (Insertion Sort)
 *
 * Task:
 * Teams register for the hackathon one by one. Each Team has a name and a size.
 * The organisers want the list ordered by team size, smallest first, but teams
 * of the same size must stay in the order in which they registered.
 * Implement void sortTeams(Team[] teams) that sorts the array in place by shifting
 * larger teams one place to the right and dropping each team into the gap,
 * which keeps equal sizes in order.
 *
 * Complexity Analysis:
 * - Time Complexity:
 *     - Best Case: O(N) when the array is already sorted (only 1 comparison per element, no shifts).
 *     - Average Case: O(N^2)
 *     - Worst Case: O(N^2) when the array is reverse sorted (shifts all preceding elements).
 * - Extra Space Complexity: O(1) auxiliary space (in-place sorting).
 * - Stability: Stable. We shift earlier teams right only while `teams[j].getSize() > key.getSize()`.
 *   Using strict inequality `>` instead of `>=` ensures that teams with equal sizes preserve their
 *   relative registration order.
 */
public class HackathonRegistrationDesk {

    public static class Team {
        private final String name;
        private final int size;

        public Team(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return name + "(" + size + ")";
        }
    }

    /**
     * Sorts teams in place by team size ascending using stable insertion sort.
     *
     * @param teams array of registered teams
     */
    public static void sortTeams(Team[] teams) {
        if (teams == null || teams.length <= 1) {
            return;
        }

        for (int i = 1; i < teams.length; i++) {
            Team key = teams[i];
            int j = i - 1;

            // Shift earlier teams right only while strictly greater than the key's size
            while (j >= 0 && teams[j].getSize() > key.getSize()) {
                teams[j + 1] = teams[j];
                j--;
            }
            teams[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Team[] teams = {
            new Team("Pixel", 4),
            new Team("Byte", 2),
            new Team("Nova", 4),
            new Team("Echo", 3),
            new Team("Atom", 2)
        };

        System.out.println("Before sorting: " + Arrays.toString(teams));
        sortTeams(teams);
        System.out.println("After sorting:  " + Arrays.toString(teams));
        // Expected Output: Byte(2), Atom(2), Echo(3), Pixel(4), Nova(4)
    }
}