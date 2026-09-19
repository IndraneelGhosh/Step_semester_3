package week_three.class_problems;

import java.util.Scanner;

/**
 * Problem 2: CSV Student Record Parser
 * 
 * Scenario:
 * The T&P team receives student registration data as CSV lines and needs a quick
 * parser to split each line into fields and print a formatted record.
 * 
 * Task:
 * - Accept a CSV line in the form "Name,RollNumber,Department".
 * - Use split(",") to break it into fields.
 * - Validate that exactly 3 fields are present; if not, print "Invalid Record".
 * - Print a formatted record: "Name: ... | Roll No: ... | Dept: ...".
 */
public class CSVStudentRecordParser {

    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String rollNumber = fields[1].trim();
        String department = fields[2].trim();

        System.out.println("Name: " + name + " | Roll No: " + rollNumber + " | Dept: " + department);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CSV Student Record Parser ===");
        System.out.print("Enter CSV record: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            parseStudentRecord(input);
        } else {
            parseStudentRecord("Ananya Verma,RA2211003010123,CSE");
            parseStudentRecord("Ananya Verma,CSE");
        }

        scanner.close();
    }
}