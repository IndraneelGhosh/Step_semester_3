package week_eight.assignment_systems;

/**
 * Question 4: The FitZone Membership Desk
 */
abstract class MembershipPlan {
    protected static final double BASE_MONTHLY_RATE = 1000.00;

    public abstract String getPlanName();
    public abstract int getDurationMonths();
    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {
    public String getPlanName() { return "Monthly"; }
    public int getDurationMonths() { return 1; }
    public double calculateFee() {
        return BASE_MONTHLY_RATE * 1.0; // Full price: ₹1,000
    }
}

class QuarterlyPlan extends MembershipPlan {
    public String getPlanName() { return "Quarterly"; }
    public int getDurationMonths() { return 3; }
    public double calculateFee() {
        return (BASE_MONTHLY_RATE * 3) * 0.90; // 10% off: ₹2,700
    }
}

class AnnualPlan extends MembershipPlan {
    public String getPlanName() { return "Annual"; }
    public int getDurationMonths() { return 12; }
    public double calculateFee() {
        return (BASE_MONTHLY_RATE * 12) * 0.75; // 25% off: ₹9,000
    }
}

class Member {
    private final String name;
    public Member(String name) { this.name = name; }
    public String getName() { return name; }
}

class Membership {
    enum Status { ACTIVE, FROZEN, EXPIRED }

    private final Member member;
    private final MembershipPlan plan;
    private Status status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = Status.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: Active.%n",
                plan.getPlanName(), member.getName(), plan.calculateFee());
    }

    public void checkIn() {
        if (status == Status.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + formatStatus(status) + ".");
        }
    }

    public void freeze() {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status == Status.ACTIVE) {
            status = Status.FROZEN;
            System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
        } else {
            System.out.println("Membership is already Frozen.");
        }
    }

    public void unfreeze() {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status == Status.FROZEN) {
            status = Status.ACTIVE;
            System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
        }
    }

    public void expire() {
        status = Status.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }

    private String formatStatus(Status s) {
        switch (s) {
            case ACTIVE: return "Active";
            case FROZEN: return "Frozen";
            case EXPIRED: return "Expired";
            default: return "";
        }
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys Quarterly membership
        Membership ashaMem = new Membership(asha, new QuarterlyPlan());

        // Ravi buys Monthly membership
        Membership raviMem = new Membership(ravi, new MonthlyPlan());

        // Asha checks in
        ashaMem.checkIn();

        // Asha freezes her membership
        ashaMem.freeze();

        // Asha attempts to check in
        ashaMem.checkIn();

        // Ravi's membership expires
        raviMem.expire();

        // Ravi attempts to freeze his membership
        raviMem.freeze();
    }
}