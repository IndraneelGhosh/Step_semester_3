package week_five.class_problems;

/**
 * Problem 4: MovieBookingProfile JavaBean & OTP Property
 *
 * Requirements:
 * - Public no-arg constructor, plus a convenience constructor chaining via this(...).
 * - JavaBean compliant getX()/setX() pair, and isConfirmed() for boolean.
 * - otp is write-only via setOtp(String otp) with no getter anywhere.
 */
public class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp; // Write-only property

    public MovieBookingProfile() {
        // No-argument constructor
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static void main(String[] args) {
        MovieBookingProfile p1 = new MovieBookingProfile("Rahul Dev");
        System.out.println("Name: " + p1.getName());

        MovieBookingProfile p2 = new MovieBookingProfile("Rahul Dev");
        p2.setConfirmed(true);
        System.out.println("isConfirmed(): " + p2.isConfirmed());

        p2.setOtp("4471"); // Write-only OTP
    }
}