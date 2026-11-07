package week_eight.practice_systems;

/**
 * Question 1: Vehicle Rental System
 */
abstract class Vehicle {
    private final String name;
    private boolean isAvailable = true;

    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public abstract double calculateRental(int days);
}

class Sedan extends Vehicle {
    public Sedan(String name) { super(name); }
    public double calculateRental(int days) { return days * 50.00; }
}

class SUV extends Vehicle {
    public SUV(String name) { super(name); }
    public double calculateRental(int days) { return days * 80.00; }
}

class Truck extends Vehicle {
    public Truck(String name) { super(name); }
    public double calculateRental(int days) { return days * 100.00; }
}

class RentalCustomer {
    private final String name;
    public RentalCustomer(String name) { this.name = name; }
    public String getName() { return name; }
}

class RentalAgency {
    public static void rentVehicle(RentalCustomer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getName() + " is currently unavailable.");
            return;
        }

        vehicle.setAvailable(false);
        double charge = vehicle.calculateRental(days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                vehicle.getName(), customer.getName(), charge);
    }

    public static void returnVehicle(RentalCustomer customer, Vehicle vehicle) {
        vehicle.setAvailable(true);
        System.out.println(vehicle.getName() + " returned by " + customer.getName() + ".");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalCustomer c1 = new RentalCustomer("Customer 1");
        RentalCustomer c2 = new RentalCustomer("Customer 2");
        RentalCustomer c3 = new RentalCustomer("Customer 3");

        Sedan sedanA = new Sedan("Sedan A");
        SUV suvB = new SUV("SUV B");

        // Customer 1 rents Sedan A for 3 days
        RentalAgency.rentVehicle(c1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days
        RentalAgency.rentVehicle(c2, sedanA, 2);

        // Customer 1 returns Sedan A
        RentalAgency.returnVehicle(c1, sedanA);

        // Customer 3 rents SUV B for 5 days
        RentalAgency.rentVehicle(c3, suvB, 5);
    }
}