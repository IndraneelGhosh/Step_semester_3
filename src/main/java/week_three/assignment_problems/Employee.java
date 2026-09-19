package week_three.assignment_problems;

/**
 * M3. Overloaded Constructors for an Employee
 *
 * Scenario:
 * Interns join without a fixed salary structure yet; permanent employees join with
 * a known salary from day one. Support both without writing the same setup logic twice.
 */
public class Employee {
    String empId;
    String empName;
    double salary;
    boolean isIntern;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    public Employee(String empId, String empName) {
        this(empId, empName, 0.0); // chains via this(...)
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }

    public static void main(String[] args) {
        Employee permanent = new Employee("E-101", "Divya", 65000.0);
        Employee intern = new Employee("E-102", "Arjun");

        permanent.printProfile();
        intern.printProfile();
    }
}