package week_three.assignment_problems;

import java.util.Scanner;

/**
 * Problem 3: Product Inventory CSV Parser
 * 
 * Scenario:
 * The warehouse team receives inventory updates as CSV lines and needs a quick
 * parser to split each line into fields and print a formatted record.
 * 
 * Task:
 * - Accept a CSV line in the form "ProductName,SKU,Quantity".
 * - Use split(",") to break it into fields.
 * - Validate that exactly 3 fields are present; if not, print "Invalid Record".
 * - Print a formatted record: "Product: ... | SKU: ... | Qty: ...".
 */
public class ProductInventoryCSVParser {

    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String qty = fields[2].trim();

        System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + qty);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Product Inventory CSV Parser ===");
        System.out.print("Enter CSV line: ");

        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            parseInventoryRecord(input);
        } else {
            parseInventoryRecord("Wireless Mouse,WM-2201,150");
            parseInventoryRecord("Wireless Mouse,150");
        }

        scanner.close();
    }
}