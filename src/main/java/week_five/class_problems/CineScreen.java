package week_five.class_problems;

/**
 * Problem 3: Seat Booking Encapsulation Guard
 *
 * Requirements:
 * - seatsTotal and seatsAvailable must both be private.
 * - Constructor must reject seatsTotal <= 0.
 * - bookSeat() and cancelBooking() must each silently reject transitions out of bounds.
 */
public class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            System.out.println("construction rejected");
            this.seatsTotal = 0;
            this.seatsAvailable = 0;
        } else {
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static void main(String[] args) {
        System.out.print("new CineScreen(0): ");
        new CineScreen(0);

        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat(); // 3rd booking rejected
        System.out.println("Seats available after 3 bookings: " + c.getSeatsAvailable());

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking(); // 3rd cancel rejected (already at full capacity)
        System.out.println("Seats available after 3 cancellations: " + c.getSeatsAvailable());
    }
}