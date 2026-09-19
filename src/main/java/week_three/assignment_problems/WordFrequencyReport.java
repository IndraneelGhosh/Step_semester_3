package week_three.assignment_problems;

import java.util.*;

/**
 * Problem 5: Stop-Word-Filtered Word Frequency Report
 * 
 * Scenario:
 * The T&P team wants word-frequency analysis of feedback paragraphs, but common filler
 * words ("the", "was", "and", "a", "is", "of", "in") should be excluded so the report
 * highlights meaningful themes instead of function words.
 * 
 * Task:
 * - Accept a paragraph of feedback text, and treat a small fixed list of stop words as filler:
 *   {"the", "was", "and", "a", "is", "of", "in"}.
 * - Normalize it: convert to lowercase, and strip punctuation such as periods and commas using replace().
 * - Split the cleaned text into words using split("\\s+").
 * - Skip any word found in the stop-word list.
 * - Count the frequency of every remaining unique word (a HashMap is fine).
 * - Print each unique word with its count, sorted by count in descending order. Ties may appear in any order.
 */
public class WordFrequencyReport {

    private static final Set<String> STOP_WORDS = new HashSet<>(
        Arrays.asList("the", "was", "and", "a", "is", "of", "in")
    );

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        // Normalize: convert to lowercase and strip punctuation using replace()
        String normalized = feedback.toLowerCase()
                                    .replace(".", "")
                                    .replace(",", "")
                                    .replace("!", "")
                                    .replace("?", "")
                                    .replace(";", "")
                                    .replace(":", "")
                                    .trim();

        // Split cleaned text into words using whitespace regex
        String[] words = normalized.split("\\s+");

        // Count frequencies with HashMap, skipping stop words
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty() || STOP_WORDS.contains(word)) {
                continue;
            }
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Sort by count in descending order
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Print each unique word with its count
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Stop-Word-Filtered Word Frequency Report ===");
        System.out.println("Enter feedback paragraph (or press Enter for demo):");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                printFilteredWordFrequency(input);
            } else {
                runDemo();
            }
        } else {
            runDemo();
        }

        scanner.close();
    }

    private static void runDemo() {
        String demo = "The mentor was great, the session was great and clear.";
        System.out.println("Demo Input:\n\"" + demo + "\"\n");
        System.out.println("Word Frequency Output:");
        printFilteredWordFrequency(demo);
    }
}