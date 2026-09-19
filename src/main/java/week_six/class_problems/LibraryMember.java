package week_six.class_problems;

import java.util.Arrays;

/**
 * LibraryMember base class.
 * Covers:
 * - Constructor validation (rejects blank, whitespace-only, or < 4 characters)
 * - Protected fields: memberId, borrowLimit, booksBorrowed
 * - Fine history ledger with defensive copying
 * - Static membership numbering and enrollment tracking
 * - Batch operations: enrollBatch, classifyGeneration, getTotalBooksBorrowed, batchPrint, processNightlyAudit
 */
public class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    // Fine history ledger (max 10 entries)
    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    // Static counter for automatic membership numbering
    private static int enrolledCounter = 100;
    private static int totalEnrolledCount = 0;
    public final String memberNumber;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must be at least 4 non-whitespace characters");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        totalEnrolledCount++;
        this.memberNumber = "LIB-" + (++enrolledCounter);
    }

    public LibraryMember(int borrowLimit) {
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
        totalEnrolledCount++;
        this.memberNumber = "LIB-" + (++enrolledCounter);
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public void borrowBook(String genre) {
        // Records genre before delegating to the no-argument overload
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount); // Defensive copy
    }

    public int getTotalFine() {
        int sum = 0;
        for (int i = 0; i < fineCount; i++) {
            sum += fineHistory[i];
        }
        return sum;
    }

    public static int getMembersEnrolled() {
        return totalEnrolledCount;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new LibraryMember(id, borrowLimit);
                    enrolled++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Child class (2 generations deep)";
        }
        return "Base class";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        if (members != null) {
            for (LibraryMember m : members) {
                if (m != null) {
                    total += m.getBooksBorrowed();
                }
            }
        }
        return total;
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) continue;
                if (m instanceof StudentMember) {
                    StudentMember sm = (StudentMember) m;
                    sb.append("Student | Course: ").append(sm.getCourse())
                      .append(" | Books: ").append(sm.getBooksBorrowed())
                      .append(" [Course via downcast: ").append(sm.getCourse()).append("] | ");
                } else {
                    sb.append("General | Books: ").append(m.getBooksBorrowed()).append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof FacultyMember) {
                        facultyCount++;
                    } else {
                        regularCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               facultyCount + " faculty | " + regularCount + " regular";
    }
}