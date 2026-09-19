package week_four.class_problems;

/**
 * M1. Library Book Cataloguing
 *
 * Scenario:
 * A librarian receives a batch of book entries in one sitting — some already have
 * a confirmed ISBN; some don't yet.
 *
 * Requirement:
 * - LibraryBook must offer two constructors linked through this() chaining — no
 *   duplicated field-setting logic between them.
 * - A book with no confirmed ISBN must default to "PENDING", never left blank or null.
 * - Every entry in the batch must be processed and its status printed in a single pass.
 */
public class LibraryBook {
    String title;
    String isbn;
    boolean catalogued;

    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = (isbn == null || isbn.trim().isEmpty()) ? "PENDING" : isbn;
        this.catalogued = true;
    }

    public LibraryBook(String title) {
        this(title, "PENDING"); // chains via this(...)
    }

    public void printStatus() {
        System.out.println(title + " | " + isbn + " | Catalogued: " + catalogued);
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i] != null && !isbns[i].trim().isEmpty()) {
                book = new LibraryBook(titles[i], isbns[i]);
            } else {
                book = new LibraryBook(titles[i]);
            }
            book.printStatus();
        }
    }
}