package week_four.class_problems;

/**
 * M2. Payroll Batch Bonus Round
 *
 * Scenario:
 * Payroll is giving every employee on a small team the same festival bonus this month.
 *
 * Requirement:
 * - Employee's constructor and raiseSalary() must each resolve a genuine field/parameter
 *   naming clash using this.
 * - Every employee in the array must receive the identical bonus amount in a single pass.
 * - Each employee's final salary must be printed after the raise is applied.
 */
public class EmployeeBonus {
    String id;
    double salary;

    public EmployeeBonus(String id, double salary) {
        this.id = id;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void printSalary() {
        System.out.println(this.id + " | Final Salary: Rs " + this.salary);
    }

    public static void main(String[] args) {
        EmployeeBonus[] employees = {
            new EmployeeBonus("E-101", 40000),
            new EmployeeBonus("E-102", 55000),
            new EmployeeBonus("E-103", 62000),
            new EmployeeBonus("E-104", 48000)
        };

        double bonus = 5000;
        for (EmployeeBonus emp : employees) {
            emp.raiseSalary(bonus);
            emp.printSalary();
        }
    }
}