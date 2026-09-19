package week_three.assignment_problems;

import java.util.Scanner;

/**
 * Problem 4: Library ISBN Normalizer & Validator
 * 
 * Scenario:
 * A library system's book-intake scanner needs to both normalize and validate ISBN-style codes.
 * A valid code is exactly 13 characters: 3 letters (publisher code) + 4 digits (year) + 6 digits (catalog number).
 * Scanned codes sometimes have stray spaces or a mixed-case publisher code.
 * 
 * Task:
 * - Accept a raw code string that may contain leading/trailing spaces.
 * - normalizeCode(): trim() the spaces, then uppercase only the first 3 characters using
 *   substring() + concatenation — leave the rest untouched.
 * - validateAndFormat():
 *   * Validate: exactly 13 characters after normalization.
 *   * First 3 characters are letters; remaining 10 are digits (use Character.isLetter() / isDigit() in a loop — no regex).
 *   * If valid, build formatted display line with StringBuilder: "[PUBCODE] YEAR: 20XX | CATALOG: 123456".
 *   * If invalid, print the specific reason: wrong length, non-letter publisher code, or non-digit body.
 */
public class LibraryIsbnValidator {

    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            String msg = "Invalid: wrong length";
            System.out.println(msg);
            return msg;
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                String msg = "Invalid: publisher code must be 3 letters";
                System.out.println(msg);
                return msg;
            }
        }

        // Validate remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                String msg = "Invalid: non-digit body";
                System.out.println(msg);
                return msg;
            }
        }

        // Build formatted display line using StringBuilder
        // Layout: [0..2] publisher code, [3..6] year, [7..12] catalog number
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(year).append(" | CATALOG: ").append(catalog);

        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Library ISBN Normalizer & Validator ===");
        System.out.print("Enter raw code: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String normalized = normalizeCode(input);
            validateAndFormat(normalized);
        } else {
            String code1 = normalizeCode(" pen2026004251 ");
            validateAndFormat(code1);

            String code2 = normalizeCode("12N2026004251");
            validateAndFormat(code2);
        }

        scanner.close();
    }
}