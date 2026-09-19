package week_five.assignment_problems;

/**
 * Problem 3: Book Copy Circulation Guard
 *
 * Requirements:
 * - copiesTotal and copiesAvailable must both be private.
 * - checkOut() silently rejected if copiesAvailable == 0.
 * - checkIn() silently rejected if copiesAvailable == copiesTotal.
 * - No direct modification of copiesAvailable from outside.
 */
public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt silently rejected
        System.out.println("Copies available after 4 checkouts: " + b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // 4th attempt silently rejected (already at capacity 3)
        System.out.println("Copies available after 4 check-ins:  " + b.getCopiesAvailable());
    }
}