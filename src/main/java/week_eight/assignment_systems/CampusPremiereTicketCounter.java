package week_eight.assignment_systems;

import java.util.*;

/**
 * Question 3: The Campus Premiere Ticket Counter
 */
abstract class Seat {
    private final String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) { super(seatNumber); }
    public double getPrice() { return 150.00; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) { super(seatNumber); }
    public double getPrice() { return 250.00; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) { super(seatNumber); }
    public double getPrice() { return 400.00; }
}

class AuditoriumShow {
    private final String showTime;
    private final Set<String> bookedSeatNumbers = new HashSet<>();
    private boolean showStarted = false;

    public AuditoriumShow(String showTime) {
        this.showTime = showTime;
    }

    public boolean isShowStarted() { return showStarted; }
    public void setShowStarted(boolean started) { this.showStarted = started; }

    public synchronized boolean isAvailable(String seatNumber) {
        return !bookedSeatNumbers.contains(seatNumber);
    }

    public synchronized void book(String seatNumber) {
        bookedSeatNumbers.add(seatNumber);
    }

    public synchronized void release(String seatNumber) {
        bookedSeatNumbers.remove(seatNumber);
    }
}

class TicketBooking {
    private final String customerName;
    private final AuditoriumShow show;
    private final List<Seat> bookedSeats;
    private final double totalAmount;

    public TicketBooking(String customerName, AuditoriumShow show, List<Seat> seats) {
        this.customerName = customerName;
        this.show = show;
        this.bookedSeats = new ArrayList<>(seats);

        double total = 0;
        for (Seat s : seats) {
            total += s.getPrice();
        }
        this.totalAmount = total;
    }

    public List<Seat> getBookedSeats() { return bookedSeats; }

    public void cancel() {
        if (show.isShowStarted()) {
            System.out.println("Cannot cancel booking: Show has already started.");
            return;
        }

        List<String> labels = new ArrayList<>();
        for (Seat s : bookedSeats) {
            show.release(s.getSeatNumber());
            labels.add(s.getSeatNumber());
        }
        System.out.println(customerName + "'s booking cancelled. Seats " + String.join(", ", labels) + " released.");
    }
}

class TicketCounter {
    public static TicketBooking createBooking(String customerName, AuditoriumShow show, List<Seat> requestedSeats) {
        if (requestedSeats.size() > 6) {
            System.out.println("Cannot book more than 6 seats per booking.");
            return null;
        }

        for (Seat s : requestedSeats) {
            if (!show.isAvailable(s.getSeatNumber())) {
                System.out.println("Seat " + s.getSeatNumber() + " is already booked for this show.");
                return null;
            }
        }

        List<String> labels = new ArrayList<>();
        for (Seat s : requestedSeats) {
            show.book(s.getSeatNumber());
            labels.add(s.getSeatNumber());
        }

        TicketBooking booking = new TicketBooking(customerName, show, requestedSeats);
        double total = 0;
        for (Seat s : requestedSeats) total += s.getPrice();

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customerName, String.join(", ", labels), total);
        return booking;
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        AuditoriumShow show7PM = new AuditoriumShow("7 PM");

        // Asha books Regular A1, A2 and Premium F5
        List<Seat> ashaSeats = Arrays.asList(new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        TicketBooking ashaBooking = TicketCounter.createBooking("Asha", show7PM, ashaSeats);

        // Ravi attempts to book A2 for same show
        TicketCounter.createBooking("Ravi", show7PM, Collections.singletonList(new RegularSeat("A2")));

        // Ravi books Recliner R1
        TicketCounter.createBooking("Ravi", show7PM, Collections.singletonList(new ReclinerSeat("R1")));

        // Asha cancels her booking before show starts
        ashaBooking.cancel();

        // Neha books seat A2
        TicketCounter.createBooking("Neha", show7PM, Collections.singletonList(new RegularSeat("A2")));
    }
}