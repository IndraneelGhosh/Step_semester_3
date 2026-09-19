package week_three.assignment_problems;

/**
 * M5. Instance vs Static: Splitting an Employee Class Correctly
 *
 * Scenario:
 * A trainee developer's first draft of Employee stores empName, salary, AND the company
 * name as instance fields — meaning every single employee object ends up with its own
 * copy of "Bright Horizon Technologies" typed in separately. Fix the design.
 *
 * Note: Named CompanyEmployee to avoid collision with M3 Employee in the same package.
 */
public class CompanyEmployee {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public CompanyEmployee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        CompanyEmployee emp1 = new CompanyEmployee("Kavya", 55000.0);
        CompanyEmployee emp2 = new CompanyEmployee("Siddharth", 62000.0);
        CompanyEmployee emp3 = new CompanyEmployee("Meera", 48000.0);

        // Access static method through the class name, not an instance
        CompanyEmployee.printCompanyInfo();
    }
}