package week_five.class_problems;

import java.util.Arrays;

/**
 * Problem 5: Immutable Booking Receipt & Nightly Settlement
 *
 * Requirements:
 * - BookingReceipt's fields are final and seatNumbers is defensively copied in and out.
 * - withUpdatedSeat(...) returns a brand-new object.
 * - processNightlySettlement(...) uses instanceof to handle GroupBookingReceipt and regular receipts,
 *   safely skipping nulls.
 */
public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = (seatNumbers != null) ? seatNumbers.clone() : new String[0];
    }

    public final String getBookingId() {
        return bookingId;
    }

    public final String[] getSeatNumbers() {
        return seatNumbers.clone(); // Defensive copy out
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updated = this.seatNumbers.clone();
        if (index >= 0 && index < updated.length) {
            updated[index] = newSeat;
        }
        return new BookingReceipt(this.bookingId, updated);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (receipts != null) {
            for (BookingReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof GroupBookingReceipt) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X"; // Attempted external mutation
        System.out.println("Defensive copy test (b.getSeatNumbers()[0]): " + b.getSeatNumbers()[0]);

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Original seats: " + Arrays.toString(b.getSeatNumbers()));
        System.out.println("Updated seats:  " + Arrays.toString(updated.getSeatNumbers()));

        BookingReceipt[] batch = new BookingReceipt[] {
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println("Nightly Settlement: " + processNightlySettlement(batch));
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}