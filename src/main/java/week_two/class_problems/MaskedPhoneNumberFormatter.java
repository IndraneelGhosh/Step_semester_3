package week_two.class_problems;

import java.util.Scanner;

/**
 * Problem 4: Masked Phone Number Formatter
 * 
 * Scenario:
 * A student-support call center displays a partially masked version of a registered phone
 * number on-screen for privacy, while agents confirm identity using the last 4 digits.
 * 
 * Task:
 * - Accept a phone number as a string.
 * - Validate that it is exactly 10 digits (all numeric).
 * - Build a masked version showing "XXXXXX" followed by the last 4 digits, using StringBuilder.
 * - Insert a "-" between the mask and the last 4 digits for readability.
 * - Print the final masked number, or an error message if validation fails.
 */
public class MaskedPhoneNumberFormatter {

    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            String err = "Invalid phone number";
            System.out.println(err);
            return err;
        }

        // Validate all characters are digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                String err = "Invalid phone number";
                System.out.println(err);
                return err;
            }
        }

        // Build masked string using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append(phone.substring(6)); // last 4 digits
        sb.insert(6, "-"); // insert '-' between mask and last 4 digits

        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Masked Phone Number Formatter ===");
        System.out.print("Enter 10-digit phone number: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            maskPhoneNumber(input);
        } else {
            maskPhoneNumber("9876543210");
            maskPhoneNumber("98765");
        }

        scanner.close();
    }
}