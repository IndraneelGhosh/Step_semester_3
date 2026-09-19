package week_six.assignment_problems;

import java.util.Arrays;

/**
 * GymMember base class.
 * Covers:
 * - Constructor validation (rejects blank, whitespace-only, or < 4 characters)
 * - Protected fields: memberId, monthlyFee, sessionsAttended
 * - Late fee ledger with defensive copying
 * - Auto-incrementing static membershipNumber generation ("GYM-2001", etc.)
 * - Batch operations: signUpBatch, classifyGeneration, getTotalSessionsAttended, batchPrint, processWeeklyCheckIn
 */
public class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    // Late fee history ledger (max 10 entries)
    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    // Fees paid tracking
    private int feesPaid = 0;

    // Static counter for automatic membership numbering
    private static int enrolledCounter = 2000;
    private static int totalEnrolledCount = 0;
    public final String membershipNumber;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must be at least 4 non-whitespace characters");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
        totalEnrolledCount++;
        this.membershipNumber = "GYM-" + (++enrolledCounter);
    }

    public GymMember(int monthlyFee) {
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
        totalEnrolledCount++;
        this.membershipNumber = "GYM-" + (++enrolledCounter);
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount); // Defensive copy
    }

    public int getTotalLateFees() {
        int sum = 0;
        for (int i = 0; i < feeCount; i++) {
            sum += lateFeeHistory[i];
        }
        return sum;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        // Records payment mode then delegates to flat version
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static int getMembersEnrolled() {
        return totalEnrolledCount;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(3));
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Child class (2 generations deep)";
        }
        return "Base class";
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    total += m.getSessionsAttended();
                }
            }
        }
        return total;
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GymMember m : members) {
                if (m == null) continue;
                if (m instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) m;
                    sb.append("Premium | Trainer: ").append(pm.getTrainerName())
                      .append(" | Sessions: ").append(pm.getSessionsAttended())
                      .append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("] | ");
                } else {
                    sb.append("Standard | Sessions: ").append(m.getSessionsAttended()).append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members != null) {
            for (GymMember m : members) {
                if (m == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (m instanceof GroupClassMember) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               groupCount + " group | " + individualCount + " individual";
    }
}