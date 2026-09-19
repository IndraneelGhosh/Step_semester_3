package week_five.assignment_problems;

/**
 * LibraryMember demonstrating proper access modifier choices:
 * - membershipPin: private (inaccessible from any other class)
 * - branchCode: default (package-private, reachable by same package)
 * - finesOwed: protected (reachable by same package and subclasses)
 * - displayName: public (reachable from anywhere)
 */
public class LibraryMemberFields {
    private int membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMemberFields(int membershipPin, String branchCode, double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public int getMembershipPin() {
        return membershipPin;
    }
}