package week_six.assignment_problems;

/**
 * PremiumMember: directly extends GymMember (single inheritance).
 */
public class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }

    @Override
    protected void chargeLateFee(int amount) {
        // Halves the late fee before delegating to super
        super.chargeLateFee(amount / 2);
    }
}