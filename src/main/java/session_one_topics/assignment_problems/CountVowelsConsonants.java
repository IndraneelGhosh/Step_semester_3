package session_one_topics.assignment_problems;

import java.util.Scanner;

/**
 * Problem: Count Vowels and Consonants
 * 
 * Counts the number of vowels (A, E, I, O, U) and consonants in an input string.
 * Ignores digits, whitespace, and special characters.
 */
public class CountVowelsConsonants {

    public static class CountResult {
        private final int vowels;
        private final int consonants;

        public CountResult(int vowels, int consonants) {
            this.vowels = vowels;
            this.consonants = consonants;
        }

        public int getVowels() {
            return vowels;
        }

        public int getConsonants() {
            return consonants;
        }

        @Override
        public String toString() {
            return "Vowels: " + vowels + ", Consonants: " + consonants;
        }
    }

    /**
     * Counts vowels and consonants in a given string.
     *
     * @param str the input text
     * @return a CountResult containing the counts
     */
    public static CountResult count(String str) {
        if (str == null) {
            return new CountResult(0, 0);
        }

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = Character.toLowerCase(str.charAt(i));

            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        return new CountResult(vowels, consonants);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Count Vowels and Consonants ===");
        System.out.print("Enter a string: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            CountResult result = count(input);
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Result: " + result);
        } else {
            String demo = "Hello World 2026!";
            System.out.println("Demo Input: \"" + demo + "\"");
            System.out.println("Result: " + count(demo));
        }

        scanner.close();
    }
}