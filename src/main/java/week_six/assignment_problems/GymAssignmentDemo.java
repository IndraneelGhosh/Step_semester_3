package week_six.assignment_problems;

/**
 * Demonstration runner verifying all 5 problems from Category B Assignment.
 */
public class GymAssignmentDemo {
    public static void main(String[] args) {
        System.out.println("=== Problem 1: Gym Membership Foundation & Batch Sign-up ===");
        try {
            new GymMember("GM1", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("new GymMember(\"GM1\", 1000): construction rejected");
        }

        PremiumMember p1 = new PremiumMember("MEM01", 2000, "Coach Riya");
        p1.attendSession();
        p1.attendSession();
        System.out.println("p1.getSessionsAttended(): " + p1.getSessionsAttended());

        String[] batch = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        System.out.println("signUpBatch: " + GymMember.signUpBatch(batch, 1000));

        System.out.println("\n=== Problem 2: Three Tiers of Gym Membership ===");
        GymMember gm = new GymMember("MEM1", 1000);
        PremiumMember pm = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember em = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember gcm = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(gm.displayInfo());
        System.out.println(pm.displayInfo());
        System.out.println(em.displayInfo());
        System.out.println(gcm.displayInfo());

        System.out.println("classifyGeneration(em):  " + GymMember.classifyGeneration(em));
        System.out.println("classifyGeneration(gcm): " + GymMember.classifyGeneration(gcm));

        // Sessions count: pm=3, em=2, gcm=4 -> 9
        pm.attendSession(); pm.attendSession(); pm.attendSession();
        em.attendSession(); em.attendSession();
        gcm.attendSession(); gcm.attendSession(); gcm.attendSession(); gcm.attendSession();
        System.out.println("getTotalSessionsAttended: " +
            GymMember.getTotalSessionsAttended(new GymMember[]{pm, em, gcm}));

        System.out.println("\n=== Problem 3: Premium Loyalty Discount & Late-Fee Ledger ===");
        PremiumMember pLate = new PremiumMember("MEM5", 2000, "Coach Riya");
        pLate.chargeLateFee(200);
        System.out.println("Total late fees: " + pLate.getTotalLateFees());

        int[] history = pLate.getLateFeeHistory();
        history[0] = 999;
        System.out.println("Defensive copy test: " + java.util.Arrays.toString(pLate.getLateFeeHistory()));

        System.out.println("\n=== Problem 4: Monthly Attendance Announcer ===");
        GymMember mem6 = new GymMember("MEM6", 1000);
        PremiumMember mem7 = new PremiumMember("MEM7", 2000, "Coach Riya");
        System.out.println(GymMember.batchPrint(new GymMember[]{mem6, mem7}));

        System.out.println("\n=== Problem 5: Membership Numbers, Referral Codes & Check-in ===");
        GymMember m1 = new GymMember(1000);
        System.out.println("m1.membershipNumber: " + m1.membershipNumber);
        System.out.println("isValidReferralCode(\"G45B\"): " + GymMember.isValidReferralCode("G45B"));
        System.out.println("isValidReferralCode(\"G4B\"):  " + GymMember.isValidReferralCode("G4B"));
        System.out.println("isValidReferralCode(\"X45B\"): " + GymMember.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("m1.getFeesPaid(): " + m1.getFeesPaid());

        GymMember[] checkInBatch = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println("processWeeklyCheckIn: " + GymMember.processWeeklyCheckIn(checkInBatch));
    }
}