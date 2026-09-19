package week_four.class_problems;

/**
 * M3. Late Fees — Skip the On-Time Accounts
 *
 * Scenario:
 * The finance office wants late fees calculated for a whole batch of accounts in
 * one run — but only for accounts that are genuinely late. An account with daysLate <= 0
 * should be skipped entirely.
 *
 * Requirement:
 * - calculateLateFee() and printSummary() must both be final, locked against ever being
 *   overridden by a subclass.
 * - An account with daysLate <= 0 must be skipped entirely, not charged a fee of Rs 0.
 * - The whole batch must be processed in a single pass, printing a summary or a skip message.
 */
public class StudentAccount {
    String regNo;
    double totalFee;

    public StudentAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    public final double calculateLateFee(int daysLate) {
        // Late fee is 1% of total fee per day late
        return totalFee * (daysLate / 100.0);
    }

    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);
            System.out.println(regNo + " | Total Fee: Rs " + totalFee + " | Late Fee: Rs " + lateFee);
        }
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            StudentAccount account = new StudentAccount(regNos[i], totalFees[i]);
            account.printSummary(daysLate[i]);
        }
    }
}