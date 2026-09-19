package week_four.assignment_problems;

/**
 * A4. Static Block — Library Membership Card Setup
 *
 * Scenario:
 * At the start of every semester, the campus library issues membership cards to a fresh batch
 * of students — and wants the library's name and the card's validity year set exactly once.
 *
 * Task:
 * - Define a class MembershipCard with static fields libraryName and validUntil, and instance field studentName.
 * - Add a static block setting libraryName to "SRM Central Library" and validUntil to "May 2027", printing "Library info loaded" once.
 * - Add constructor MembershipCard(String studentName).
 * - Loop over 5 names, create cards, and print confirmation.
 */
public class MembershipCard {
    static String libraryName;
    static String validUntil;
    String studentName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
        System.out.println("Membership card issued: " + studentName);
    }

    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            new MembershipCard(name);
        }
    }
}