package week_three.class_problems;

import java.util.Scanner;

/**
 * Problem 5: Bank Transaction Reference Generator & Validator
 * 
 * Scenario:
 * A fintech onboarding module for a placement-prep hackathon needs to both normalize
 * and validate transaction reference codes. A valid reference is exactly 14 characters:
 * 3 letters (bank code) + 6 digits (date, ddMMyy) + 5 digits (sequence number).
 * 
 * Task:
 * - Accept a raw reference string that may contain leading/trailing spaces.
 * - normalizeReference(): trim() the spaces, then uppercase only the first 3 characters
 *   using substring() + concatenation — leave the rest untouched.
 * - validateAndFormat():
 *   * Validate: exactly 14 characters after normalization.
 *   * First 3 characters are letters; remaining 11 are digits (use Character.isLetter() / isDigit() in a loop — no regex).
 *   * If valid, build formatted display line with StringBuilder: "[BANKCODE] DATE: dd/MM/yy | SEQ: 12345".
 *   * If invalid, print the specific reason: wrong length, non-letter bank code, or non-digit body.
 */
public class BankTransactionValidator {

    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            String msg = "Invalid: wrong length";
            System.out.println(msg);
            return msg;
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                String msg = "Invalid: bank code must be 3 letters";
                System.out.println(msg);
                return msg;
            }
        }

        // Validate remaining 11 characters are digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                String msg = "Invalid: non-digit body";
                System.out.println(msg);
                return msg;
            }
        }

        // Build formatted display line using StringBuilder
        // Reference layout: [0..2] bank code, [3..8] ddMMyy date, [9..13] sequence
        String bankCode = reference.substring(0, 3);
        String dd = reference.substring(3, 5);
        String mm = reference.substring(5, 7);
        String yy = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
          .append(dd).append("/").append(mm).append("/").append(yy)
          .append(" | SEQ: ").append(seq);

        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Bank Transaction Reference Generator & Validator ===");
        System.out.print("Enter raw transaction reference: ");

        if (scanner.hasNextLine()) {
            String raw = scanner.nextLine();
            String normalized = normalizeReference(raw);
            validateAndFormat(normalized);
        } else {
            String ref1 = normalizeReference(" hdf03022600042 ");
            validateAndFormat(ref1);

            String ref2 = normalizeReference("12F03022600042");
            validateAndFormat(ref2);
        }

        scanner.close();
    }
}