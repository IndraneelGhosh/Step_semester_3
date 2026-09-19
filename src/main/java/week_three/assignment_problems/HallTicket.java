package week_three.assignment_problems;

/**
 * M4. Reference Copies and a Shared Exam Hall Ticket
 *
 * Scenario:
 * Two "different" variables in a script both claim to represent Priya's exam hall ticket,
 * but only one of them is actually real. Prove it with code.
 */
public class HallTicket {
    String studentName;
    int seatNumber;

    public HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }

    public static void main(String[] args) {
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copy = priya;
        copy.seatNumber = 45;

        HallTicket separate = new HallTicket("Priya", 45);

        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        System.out.println("separate == priya: " + (separate == priya));
    }
}