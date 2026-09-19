package week_five.assignment_problems;

/**
 * Problem 4: LibraryMember JavaBean & Security Answer Property
 *
 * Requirements:
 * - Public no-arg constructor.
 * - JavaBean compliant getX()/setX() pairs, isPremiumMember() for boolean.
 * - setMembershipId(String id) is WRITE-ONCE: only takes effect on the first call;
 *   subsequent calls are silently ignored.
 * - setSecurityAnswer(String answer) is WRITE-ONLY: stores a one-way transformed value,
 *   no getter anywhere on the class.
 */
public class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private int securityAnswerHash; // Write-only one-way transformation

    public LibraryMemberBean() {
        // Required public no-arg constructor
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        // Write-once property
        if (this.membershipId == null) {
            this.membershipId = membershipId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String securityAnswer) {
        // One-way deterministic transformation; no getter exists
        this.securityAnswerHash = (securityAnswer != null) ? securityAnswer.hashCode() : 0;
    }

    public static void main(String[] args) {
        LibraryMemberBean m = new LibraryMemberBean();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println("Membership ID: " + m.getMembershipId());

        m.setMembershipId("FAKE-0000"); // Second call silently ignored
        System.out.println("Membership ID after re-set attempt: " + m.getMembershipId());
        System.out.println("isPremiumMember(): " + m.isPremiumMember());

        m.setSecurityAnswer("BlueMountain"); // Write-only
    }
}