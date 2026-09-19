package week_four.class_problems;

/**
 * M5. Account Batch Payments
 *
 * Scenario:
 * The finance office's nightly batch run has to process a whole list of accounts in one
 * pass — some plain FeeAccount, some HostelFeeAccount — and each type pays differently.
 *
 * Requirement:
 * - processPayment() must use instanceof to correctly dispatch between HostelFeeAccount
 *   and a plain FeeAccount.
 * - The batch must track how many of each account type were processed, using simple counters.
 * - Both counters must be printed once, after the full batch has been processed.
 */
class FeeAccount {
    // Base fee account
}

class HostelFeeAccount extends FeeAccount {
    // Specialized fee account for hostel residents
}

public class AccountBatchPayments {

    public static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            System.out.println("Paid in two installments (hostel account)");
        } else if (account instanceof FeeAccount) {
            System.out.println("Paid in one go (day-scholar account)");
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;
        int hostelCount = 0;
        int dayScholarCount = 0;

        for (FeeAccount acc : accounts) {
            processPayment(acc, amount);
            if (acc instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
        }

        System.out.println("Hostel accounts processed: " + hostelCount +
                           " | Day-scholar accounts processed: " + dayScholarCount);
    }
}