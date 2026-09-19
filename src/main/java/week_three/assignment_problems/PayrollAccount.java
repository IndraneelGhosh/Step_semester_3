package week_three.assignment_problems;

/**
 * M2. Encapsulated Payroll Account
 *
 * Scenario:
 * A company's payroll account must never let its net salary be set directly from
 * outside the class, and bonuses must never be negative.
 */
public class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: Basic salary cannot be negative. Initializing to 0.");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0.0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Credit rejected: bonus amount must be greater than 0");
            return;
        }
        this.bonus += amount;
        System.out.println("Bonus credited: Rs " + amount);
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Deduction rejected: invalid tax percentage");
            return;
        }
        this.basicSalary = this.basicSalary - (this.basicSalary * percent / 100.0);
        System.out.println("Tax deducted: " + (int)percent + "%");
    }

    public double getNetSalary() {
        return basicSalary + bonus;
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}