package session_one_topics.class_problems;

import java.util.Scanner;

/**
 * Problem: Palindrome Check
 * 
 * Checks whether a given string reads the same backward as forward.
 * Approaches:
 * 1. Two-pointer comparison from outside inwards.
 * 2. Reversed string comparison using StringBuilder.
 */
public class PalindromeCheck {

    /**
     * Checks if a string is a palindrome (exact match or case-insensitive).
     *
     * @param str             the input string
     * @param ignoreCase      whether to ignore character casing
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String str, boolean ignoreCase) {
        if (str == null) {
            return false;
        }

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            char c1 = str.charAt(left);
            char c2 = str.charAt(right);

            if (ignoreCase) {
                c1 = Character.toLowerCase(c1);
                c2 = Character.toLowerCase(c2);
            }

            if (c1 != c2) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     * Checks if a phrase is a palindrome considering only alphanumeric characters
     * and ignoring letter cases (standard interview / leetcode variation).
     *
     * @param str the input phrase
     * @return true if phrase is a valid palindrome
     */
    public static boolean isPalindromeAlphanumeric(String str) {
        if (str == null) {
            return false;
        }

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }

            char c1 = Character.toLowerCase(str.charAt(left));
            char c2 = Character.toLowerCase(str.charAt(right));

            if (c1 != c2) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Palindrome Check ===");
        System.out.print("Enter a string to test: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            boolean result = isPalindrome(input, true);
            System.out.println("\"" + input + "\" is a palindrome: " + result);
        } else {
            String demo = "Racecar";
            System.out.println("Demo Input: \"" + demo + "\"");
            System.out.println("Is Palindrome (case-insensitive): " + isPalindrome(demo, true));
        }

        scanner.close();
    }
}