package week_eight.practice_systems;

/**
 * Question 2: Employee Leave Request Workflow
 */
abstract class Employee {
    private final String name;
    public Employee(String name) { this.name = name; }
    public String getName() { return name; }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) { super(name); }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) { super(name); }
}

class Contractor extends Employee {
    public Contractor(String name) { super(name); }
}

class LeaveRequest {
    enum Status { PENDING, APPROVED, REJECTED }

    private final Employee employee;
    private final String dateRange;
    private Status status;

    public LeaveRequest(Employee employee, String dateRange) {
        this.employee = employee;
        this.dateRange = dateRange;
        this.status = Status.PENDING;
        System.out.println("Leave request submitted for " + employee.getName() + " (" + dateRange + "). Status: Pending.");
    }

    public String getDateRange() { return dateRange; }
    public Status getStatus() { return status; }

    public void approve(String managerName) {
        if (status != Status.PENDING) {
            System.out.println("Cannot change reviewed leave request.");
            return;
        }
        this.status = Status.APPROVED;
        System.out.println(employee.getName() + "'s leave request (" + dateRange + ") approved. Status: Approved.");
    }

    public void reject(String managerName) {
        if (status != Status.PENDING) {
            System.out.println("Cannot change reviewed leave request.");
            return;
        }
        this.status = Status.REJECTED;
        System.out.println(employee.getName() + "'s leave request (" + dateRange + ") rejected. Status: Rejected.");
    }

    public void setStatus(Status newStatus) {
        if ((this.status == Status.APPROVED || this.status == Status.REJECTED) && newStatus == Status.PENDING) {
            System.out.println("Cannot change leave request status from " + formatStatus(this.status) + " to Pending.");
            return;
        }
        this.status = newStatus;
    }

    private String formatStatus(Status s) {
        switch (s) {
            case APPROVED: return "Approved";
            case REJECTED: return "Rejected";
            default: return "Pending";
        }
    }
}

public class EmployeeLeaveWorkflow {
    public static void main(String[] args) {
        FullTimeEmployee john = new FullTimeEmployee("John");
        LeaveRequest johnReq = new LeaveRequest(john, "Jan 1-5");
        johnReq.approve("Alice");

        PartTimeEmployee jane = new PartTimeEmployee("Jane");
        LeaveRequest janeReq = new LeaveRequest(jane, "Feb 10-11");
        janeReq.reject("Bob");

        // John attempts to change approved request to Pending
        johnReq.setStatus(LeaveRequest.Status.PENDING);
    }
}