package week_two.class_problems;

import java.util.Scanner;

/**
 * Problem 1: Vowel & Consonant Counter
 * 
 * Scenario:
 * A library orientation kiosk counts vowels and consonants in a submitted book title
 * for a simple text-stats display.
 * 
 * Task:
 * - Accept a string (assume only letters and spaces).
 * - Loop through each character using charAt().
 * - Count vowels (a, e, i, o, u — case-insensitive) and consonants separately; ignore spaces.
 * - Print the total vowels and total consonants.
 */
public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        if (text == null) {
            System.out.println("Vowels: 0 | Consonants: 0");
            return;
        }

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));

            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Vowel & Consonant Counter ===");
        System.out.print("Enter text: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            countVowelsAndConsonants(input);
        } else {
            countVowelsAndConsonants("Java Programming");
        }

        scanner.close();
    }
}