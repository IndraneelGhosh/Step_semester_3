package week_seven.assignment_problems;

/**
 * Problem 1: Morning Wake-Up Circuit
 *
 * Requirements:
 * - Define an interface Ringable with a method ring() returning a String.
 * - AlarmClock and Doorbell must both implement Ringable directly, with no shared parent class.
 * - Write a static method ringAll(Ringable[] devices) that loops through a mixed array
 *   and calls ring() on each.
 */
public interface Ringable {
    String ring();

    static void ringAll(Ringable[] devices) {
        if (devices != null) {
            for (Ringable device : devices) {
                if (device != null) {
                    System.out.println(device.ring());
                }
            }
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring());

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring());

        System.out.println("--- ringAll demo ---");
        ringAll(new Ringable[]{ a, d });
    }
}

class AlarmClock implements Ringable {
    private String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class Doorbell implements Ringable {
    private String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}