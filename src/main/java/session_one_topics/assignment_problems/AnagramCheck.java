package session_one_topics.assignment_problems;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem: Anagram Check
 * 
 * Determines whether two strings are anagrams of each other (contain the exact same
 * characters with the same frequencies, ignoring order, spaces, and case).
 * 
 * Approaches:
 * 1. Frequency count array (O(n) time, O(1) space for 26 letters).
 * 2. Character sorting (O(n log n) time).
 */
public class AnagramCheck {

    /**
     * Checks if two strings are anagrams using a frequency table.
     * Ignores spaces and character casing.
     *
     * @param str1 first string
     * @param str2 second string
     * @return true if str1 and str2 are anagrams, false otherwise
     */
    public static boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        String s1 = str1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String s2 = str2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (s1.length() != s2.length()) {
            return false;
        }

        int[] frequency = new int[256];

        for (int i = 0; i < s1.length(); i++) {
            frequency[s1.charAt(i)]++;
            frequency[s2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().charAt(i)]--;
        }

        for (int count : frequency) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if two strings are anagrams by sorting character arrays.
     *
     * @param str1 first string
     * @param str2 second string
     * @return true if str1 and str2 are anagrams
     */
    public static boolean isAnagramUsingSorting(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }

        String s1 = str1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String s2 = str2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (s1.length() != s2.length()) {
            return false;
        }

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Anagram Check ===");

        System.out.print("Enter first string: ");
        String str1 = scanner.hasNextLine() ? scanner.nextLine() : "Listen";

        System.out.print("Enter second string: ");
        String str2 = scanner.hasNextLine() ? scanner.nextLine() : "Silent";

        boolean result = isAnagram(str1, str2);
        System.out.println("\"" + str1 + "\" and \"" + str2 + "\" are anagrams: " + result);

        scanner.close();
    }
}