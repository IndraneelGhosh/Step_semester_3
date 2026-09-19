package week_six.assignment_problems;

/**
 * EliteMember: extends PremiumMember (multilevel inheritance, 3 classes deep).
 */
public class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName() +
               " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}