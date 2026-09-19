package week_six.class_problems;

/**
 * StudentMember: directly extends LibraryMember (single inheritance).
 */
public class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
    }

    @Override
    protected void chargeFine(int amount) {
        // Halves the fine before delegating to super
        super.chargeFine(amount / 2);
    }
}