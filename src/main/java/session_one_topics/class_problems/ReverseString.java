package session_one_topics.class_problems;

import java.util.Scanner;

/**
 * Problem: Reverse a String
 * 
 * Approaches:
 * 1. Two-pointer character array swap (in-place modification, O(n) time, O(n) space).
 * 2. StringBuilder reverse (built-in Java utility).
 */
public class ReverseString {

    /**
     * Reverses the input string using a two-pointer approach on a char array.
     *
     * @param str the original string
     * @return the reversed string
     */
    public static String reverseUsingTwoPointers(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    /**
     * Reverses the input string using StringBuilder.
     *
     * @param str the original string
     * @return the reversed string
     */
    public static String reverseUsingStringBuilder(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Reverse String ===");
        System.out.print("Enter a string to reverse: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String reversed = reverseUsingTwoPointers(input);

            System.out.println("Original String : \"" + input + "\"");
            System.out.println("Reversed String : \"" + reversed + "\"");
        } else {
            String demo = "Hello World";
            System.out.println("Demo Input      : \"" + demo + "\"");
            System.out.println("Reversed Output : \"" + reverseUsingTwoPointers(demo) + "\"");
        }

        scanner.close();
    }
}