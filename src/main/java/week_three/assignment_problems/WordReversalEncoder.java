package week_three.assignment_problems;

import java.util.Scanner;

/**
 * Problem 2: Word Reversal Encoder
 * 
 * Scenario:
 * The coding club's "mirror text" mini-game reverses every word in a sentence individually
 * while keeping the word order the same, so "hello club" becomes "olleh bulc".
 * 
 * Task:
 * - Accept a sentence (words separated by single spaces).
 * - Split it into words using split(" ").
 * - For each word, build its reverse using a loop and StringBuilder.
 * - Join the reversed words back together with spaces and print the result.
 */
public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder reversedWord = new StringBuilder();

            // Reverse the individual word using a loop
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            result.append(reversedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        String finalString = result.toString();
        System.out.println(finalString);
        return finalString;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Word Reversal Encoder ===");
        System.out.print("Enter sentence: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            reverseEachWord(input);
        } else {
            reverseEachWord("hello club");
        }

        scanner.close();
    }
}