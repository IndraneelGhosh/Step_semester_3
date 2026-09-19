package week_three.class_problems;

import java.util.Scanner;

/**
 * Problem 3: File Extension Validator
 * 
 * Scenario:
 * An assignment-upload portal must check whether an uploaded filename has an accepted
 * extension (pdf, docx, zip) regardless of case, before accepting the submission.
 * 
 * Task:
 * - Accept a filename string.
 * - Find the last '.' using lastIndexOf('.') and extract the extension with substring().
 * - Compare the extension case-insensitively against the accepted list: pdf, docx, zip.
 * - Print "Accepted" or "Rejected — invalid file type".
 */
public class FileExtensionValidator {

    public static String validateFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            String result = "Rejected — invalid file type";
            System.out.println(result);
            return result;
        }

        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == filename.length() - 1) {
            String result = "Rejected — invalid file type";
            System.out.println(result);
            return result;
        }

        String extension = filename.substring(lastDotIndex + 1);

        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {
            String result = "Accepted";
            System.out.println(result);
            return result;
        } else {
            String result = "Rejected — invalid file type";
            System.out.println(result);
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== File Extension Validator ===");
        System.out.print("Enter filename: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            validateFileExtension(input);
        } else {
            validateFileExtension("Assignment1.PDF");
            validateFileExtension("notes.txt");
        }

        scanner.close();
    }
}