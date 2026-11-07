package week_eight.assignment_systems;

/**
 * Question 2: The Assignment Submission Portal
 */
abstract class Assignment {
    private final String title;
    private final int maxMarks;
    private final int dueDay; // Represented as day of month for clarity (e.g. 10 for Mar 10)

    public Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public int getDueDay() { return dueDay; }

    public abstract int calculateLatePenaltyPercent(int lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public int calculateLatePenaltyPercent(int lateDays) {
        // Loses 10% per day late
        return lateDays * 10;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public int calculateLatePenaltyPercent(int lateDays) {
        // Loses 20% per day late
        return lateDays * 20;
    }
}

class PortalStudent {
    private final String name;
    public PortalStudent(String name) { this.name = name; }
    public String getName() { return name; }
}

class Submission {
    enum Status { SUBMITTED, GRADED }

    private final PortalStudent student;
    private final Assignment assignment;
    private final int submissionDay;
    private final int lateDays;
    private Status status;
    private int finalMarks;

    public Submission(PortalStudent student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.lateDays = Math.max(0, submissionDay - assignment.getDueDay());
        this.status = Status.SUBMITTED;

        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + lateDays + " days late). Status: Submitted.");
        }
    }

    public boolean isGraded() {
        return status == Status.GRADED;
    }

    public void grade(int rawMarks) {
        if (status == Status.GRADED) {
            System.out.println("Submission is already graded.");
            return;
        }

        int penaltyPercent = assignment.calculateLatePenaltyPercent(lateDays);
        int deducted = (rawMarks * penaltyPercent) / 100;
        this.finalMarks = rawMarks - deducted;
        this.status = Status.GRADED;

        if (penaltyPercent > 0) {
            System.out.println(student.getName() + " graded: " + finalMarks + "/" + assignment.getMaxMarks() +
                               " after " + penaltyPercent + "% late penalty. Status: Graded.");
        } else {
            System.out.println(student.getName() + " graded: " + finalMarks + "/" + assignment.getMaxMarks() + ". Status: Graded.");
        }
    }

    public void attemptResubmit() {
        if (status == Status.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
        } else {
            System.out.println("Resubmission accepted.");
        }
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        CodingAssignment lab = new CodingAssignment("Linked List Lab", 50, 10);
        WrittenAssignment essay = new WrittenAssignment("Design Essay", 50, 12);

        PortalStudent asha = new PortalStudent("Asha");
        PortalStudent ravi = new PortalStudent("Ravi");

        // Asha submits on time (Mar 10)
        Submission ashaSub = new Submission(asha, lab, 10);

        // Ravi submits 2 days late (Mar 14)
        Submission raviSub = new Submission(ravi, essay, 14);

        // Faculty awards Asha 45 marks
        ashaSub.grade(45);

        // Faculty awards Ravi 40 marks
        raviSub.grade(40);

        // Asha attempts to resubmit
        ashaSub.attemptResubmit();
    }
}