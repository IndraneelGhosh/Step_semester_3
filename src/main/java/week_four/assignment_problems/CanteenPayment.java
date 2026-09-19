package week_four.assignment_problems;

/**
 * A5. instanceof Inside a Loop — Canteen Closing-Time Payment Dispatch
 *
 * Scenario:
 * At closing time, the canteen POS system replays the day's transactions in one batch —
 * some paid by cash, some by card — and card transactions carry a 2% processing fee.
 *
 * Task:
 * - Define a class Payment with a method pay(double amount) that prints the amount paid.
 * - Define a class CardPayment extends Payment, adding payWithProcessingFee(double amount) that adds 2% fee.
 * - Write processTransaction(Payment payment, double amount) using instanceof to check if CardPayment.
 * - Loop over Payment[] array with matching double[] amounts, update totalCollected running sum, and print at the end.
 */
class Payment {
    public double pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
        return amount;
    }
}

class CardPayment extends Payment {
    public double payWithProcessingFee(double amount) {
        double fee = amount * 0.02;
        double total = amount + fee;
        System.out.println("Charged (card, incl. fee): Rs " + total);
        return total;
    }
}

public class CanteenPayment {

    public static double processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cp = (CardPayment) payment;
            return cp.payWithProcessingFee(amount);
        } else {
            return payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};
        double totalCollected = 0.0;

        for (int i = 0; i < payments.length; i++) {
            totalCollected += processTransaction(payments[i], amounts[i]);
        }

        System.out.printf("Total Collected: Rs %.1f%n", totalCollected);
    }
}