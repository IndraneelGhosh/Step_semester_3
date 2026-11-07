package week_eight.practice_systems;

import java.util.*;

/**
 * Question 4: Hotel Booking System
 */
abstract class Room {
    private final String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }
    public abstract String getCategoryName();
    public abstract double calculatePrice(int nights);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) { super(roomNumber); }
    public String getCategoryName() { return "Standard Room"; }
    public double calculatePrice(int nights) { return nights * 100.00; }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) { super(roomNumber); }
    public String getCategoryName() { return "Deluxe Room"; }
    public double calculatePrice(int nights) { return nights * 180.00; }
}

class Suite extends Room {
    public Suite(String roomNumber) { super(roomNumber); }
    public String getCategoryName() { return "Suite"; }
    public double calculatePrice(int nights) { return nights * 300.00; }
}

class Reservation {
    private final String customerName;
    private final Room room;
    private final int startDay;
    private final int endDay;
    private final String dateLabel;
    private boolean active = true;

    public Reservation(String customerName, Room room, int startDay, int endDay, String dateLabel) {
        this.customerName = customerName;
        this.room = room;
        this.startDay = startDay;
        this.endDay = endDay;
        this.dateLabel = dateLabel;
    }

    public Room getRoom() { return room; }
    public int getStartDay() { return startDay; }
    public int getEndDay() { return endDay; }
    public String getDateLabel() { return dateLabel; }
    public boolean isActive() { return active; }

    public void cancel() {
        this.active = false;
        System.out.println("Reservation for " + customerName + ", " + room.getCategoryName() + " " + room.getRoomNumber() + " (" + dateLabel + ") cancelled successfully.");
    }
}

class HotelManager {
    private final List<Reservation> reservations = new ArrayList<>();

    public boolean isAvailable(Room room, int startDay, int endDay) {
        for (Reservation r : reservations) {
            if (r.isActive() && r.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                // Overlap condition: start < r.end && end > r.start
                if (startDay < r.getEndDay() && endDay > r.getStartDay()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkAvailability(Room room, int startDay, int endDay, String dateLabel) {
        if (isAvailable(room, startDay, endDay)) {
            System.out.println(room.getCategoryName() + " " + room.getRoomNumber() + " is available from " + dateLabel + ".");
        } else {
            System.out.println(room.getCategoryName() + " " + room.getRoomNumber() + " is not available from " + dateLabel + ".");
        }
    }

    public Reservation reserve(String customerName, Room room, int startDay, int endDay, String dateLabel) {
        if (!isAvailable(room, startDay, endDay)) {
            System.out.println(room.getCategoryName() + " " + room.getRoomNumber() + " is not available from " + dateLabel + ".");
            return null;
        }

        Reservation res = new Reservation(customerName, room, startDay, endDay, dateLabel);
        reservations.add(res);
        int nights = endDay - startDay;
        double price = room.calculatePrice(nights);
        System.out.printf("Reservation confirmed for %s, %s %s (%s). Price: $%.2f.%n",
                customerName, room.getCategoryName(), room.getRoomNumber(), dateLabel, price);
        return res;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        HotelManager hotel = new HotelManager();
        StandardRoom room101 = new StandardRoom("101");
        DeluxeRoom room201 = new DeluxeRoom("201");

        // Customer A checks availability Jan 1 to Jan 5 (days 1 to 5)
        hotel.checkAvailability(room101, 1, 5, "Jan 1 to Jan 5");

        // Customer A reserves Standard Room 101 from Jan 1 to Jan 5
        Reservation resA = hotel.reserve("Customer A", room101, 1, 5, "Jan 1-5");

        // Customer B attempts to reserve Standard Room 101 from Jan 3 to Jan 7 (days 3 to 7)
        hotel.reserve("Customer B", room101, 3, 7, "Jan 3 to Jan 7");

        // Customer A cancels reservation
        resA.cancel();

        // Customer C reserves Deluxe Room 201 from Feb 10 to Feb 12 (days 41 to 43, 2 nights)
        hotel.reserve("Customer C", room201, 41, 43, "Feb 10-12");
    }
}