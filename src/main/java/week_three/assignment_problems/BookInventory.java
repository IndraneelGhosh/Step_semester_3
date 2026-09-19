package week_three.assignment_problems;

/**
 * M1. From Parallel Arrays to a Class — Library Inventory
 *
 * Scenario:
 * The library currently tracks its book inventory using three parallel arrays —
 * titles, authors, copiesAvailable — and a recount last week already went out of sync.
 * Rebuild it the OOP way.
 */
public class BookInventory {
    String title;
    String author;
    int copiesAvailable;

    public BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public void printEntry() {
        System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
    }

    public static void main(String[] args) {
        BookInventory[] inventory = new BookInventory[] {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };

        for (BookInventory book : inventory) {
            book.printEntry();
        }
    }
}