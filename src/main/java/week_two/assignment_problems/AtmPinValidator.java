package week_two.assignment_problems;

import java.util.Scanner;

/**
 * Problem 1: ATM PIN Length Validator
 * 
 * Scenario:
 * An ATM app must check that a PIN a customer enters is exactly 4 digits long
 * before allowing them to continue, using only the most basic checks.
 * 
 * Task:
 * - Accept a PIN string.
 * - Get its length using length().
 * - If the length is not exactly 4, print "Invalid PIN — must be exactly 4 digits."
 * - Otherwise, print "PIN length OK."
 * - This one needs no loop at all — just length() and a single if / else.
 */
public class AtmPinValidator {

    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ATM PIN Length Validator ===");
        System.out.print("Enter PIN: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            checkPinLength(input);
        } else {
            checkPinLength("482");
            checkPinLength("4820");
        }

        scanner.close();
    }
}