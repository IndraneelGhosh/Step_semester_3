package week_five.assignment_problems;

import java.util.Arrays;

/**
 * Problem 5: Immutable Loan Receipt & Nightly Circulation Ledger
 *
 * Requirements:
 * - LoanReceipt fields are final and bookIds is defensively copied in and out.
 * - withCorrectedBookId(...) returns a brand-new object.
 * - CirculationLedger has a static initialization block.
 * - processNightlyCirculation(...) uses instanceof to handle ReferenceOnlyLoanReceipt
 *   and regular LoanReceipts, safely skipping nulls without throwing exceptions.
 */
public class CirculationLedger {
    static String branchCode;

    static {
        branchCode = "MAIN-BRANCH-01";
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnly++;
                    } else {
                        regular++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("Defensive copy test (r.getBookIds()[0]): " + r.getBookIds()[0]);

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original book IDs:  " + Arrays.toString(r.getBookIds()));
        System.out.println("Corrected book IDs: " + Arrays.toString(corrected.getBookIds()));

        LoanReceipt[] batch = new LoanReceipt[] {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println("\nNightly circulation output:\n\"" + processNightlyCirculation(batch) + "\"");
    }
}

class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = (bookIds != null) ? bookIds.clone() : new String[0];
    }

    public final String getMemberId() {
        return memberId;
    }

    public final String[] getBookIds() {
        return bookIds.clone(); // Defensive copy out
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updated = this.bookIds.clone();
        if (index >= 0 && index < updated.length) {
            updated[index] = newId;
        }
        return new LoanReceipt(this.memberId, updated);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}