package week_six.class_problems;

/**
 * Demonstration runner verifying all 5 problems from Category B Practice Problems.
 */
public class LibraryPracticeDemo {
    public static void main(String[] args) {
        System.out.println("=== Problem 1: Constructor Validation & Batch Enrollment ===");
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("new LibraryMember(\"LB1\", 3): construction rejected");
        }

        StudentMember s1 = new StudentMember("STU10", 3, "CSE");
        s1.borrowBook();
        s1.borrowBook();
        System.out.println("s1.getBooksBorrowed(): " + s1.getBooksBorrowed());

        String[] batch = {"STU1", "LB1", "STU2", " ", "STU3"};
        System.out.println("enrollBatch: " + LibraryMember.enrollBatch(batch, 3));

        System.out.println("\n=== Problem 2: Three Branches of the Membership Tree ===");
        LibraryMember lm = new LibraryMember("STU1", 3);
        StudentMember sm = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember hm = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember fm = new FacultyMember("STU4", 5, "Physics");

        System.out.println(lm.displayInfo());
        System.out.println(sm.displayInfo());
        System.out.println(hm.displayInfo());
        System.out.println(fm.displayInfo());

        System.out.println("classifyGeneration(hm): " + LibraryMember.classifyGeneration(hm));
        System.out.println("classifyGeneration(fm): " + LibraryMember.classifyGeneration(fm));

        // Total books borrowed demo
        sm.borrowBook(); // 1
        hm.borrowBook(); // 1
        fm.borrowBook(); fm.borrowBook(); fm.borrowBook(); // 3
        System.out.println("getTotalBooksBorrowed: " +
            LibraryMember.getTotalBooksBorrowed(new LibraryMember[]{sm, hm, fm}));

        System.out.println("\n=== Problem 3: Student Discount & Fine Ledger ===");
        StudentMember sFine = new StudentMember("STU5", 3, "CSE");
        sFine.chargeFine(100);
        System.out.println("Total fine: " + sFine.getTotalFine());

        int[] history = sFine.getFineHistory();
        history[0] = 999; // Attempted tampering
        System.out.println("Defensive copy test: " + java.util.Arrays.toString(sFine.getFineHistory()));

        System.out.println("\n=== Problem 4: Weekly Circulation Report ===");
        LibraryMember lb5 = new LibraryMember("LB500", 3);
        StudentMember stu6 = new StudentMember("STU6", 3, "ECE");
        System.out.println(LibraryMember.batchPrint(new LibraryMember[]{lb5, stu6}));

        System.out.println("\n=== Problem 5: Membership Numbers, Renewal Codes & Audit ===");
        LibraryMember m1 = new LibraryMember(3);
        System.out.println("m1.memberNumber: " + m1.memberNumber);
        System.out.println("isValidRenewalCode(\"R12A\"): " + LibraryMember.isValidRenewalCode("R12A"));
        System.out.println("isValidRenewalCode(\"R1A\"):  " + LibraryMember.isValidRenewalCode("R1A"));
        System.out.println("isValidRenewalCode(\"X12A\"): " + LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println("m1.getBooksBorrowed(): " + m1.getBooksBorrowed());

        LibraryMember[] auditBatch = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println("processNightlyAudit: " + LibraryMember.processNightlyAudit(auditBatch));
    }
}