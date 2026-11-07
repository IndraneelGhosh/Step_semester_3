package week_seven.assignment_problems;

/**
 * Problem 4: Digital Classroom Setup
 *
 * Requirements:
 * - ClassroomDevice must be an abstract class with an abstract method operate().
 * - Define an interface Chargeable with a method charge() and an overloaded charge(int minutes).
 * - Tablet must extend ClassroomDevice and implement Chargeable, providing both charge() methods.
 */
public abstract class ClassroomDevice {
    protected String assetTag;

    public ClassroomDevice(String assetTag) {
        this.assetTag = assetTag;
    }

    public abstract String operate();

    public String getAssetTag() {
        return assetTag;
    }

    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}

interface Chargeable {
    String charge();
    String charge(int minutes);
}

class Tablet extends ClassroomDevice implements Chargeable {
    public Tablet(String assetTag) {
        super(assetTag);
    }

    @Override
    public String operate() {
        return "Tablet " + assetTag + " displaying lesson";
    }

    @Override
    public String charge() {
        return assetTag + " charging";
    }

    @Override
    public String charge(int minutes) {
        return assetTag + " charging for " + minutes + " minutes";
    }
}