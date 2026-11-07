package week_eight.assignment_systems;

/**
 * Question 1: The Hostel Laundry Queue
 */
interface WashType {
    String getName();
    int getDurationMinutes();
    double getCharge();
}

class QuickWash implements WashType {
    public String getName() { return "Quick wash"; }
    public int getDurationMinutes() { return 30; }
    public double getCharge() { return 20.00; }
}

class NormalWash implements WashType {
    public String getName() { return "Normal wash"; }
    public int getDurationMinutes() { return 45; }
    public double getCharge() { return 30.00; }
}

class HeavyWash implements WashType {
    public String getName() { return "Heavy wash"; }
    public int getDurationMinutes() { return 60; }
    public double getCharge() { return 45.00; }
}

class Student {
    private final String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}

class WashingMachine {
    private final String id;
    private boolean isBusy;
    private WashCycle currentCycle;

    public WashingMachine(String id) {
        this.id = id;
        this.isBusy = false;
    }

    public String getId() { return id; }
    public boolean isBusy() { return isBusy; }

    public boolean startWash(Student student, WashType washType) {
        if (isBusy) {
            System.out.println("Machine " + id + " is currently busy.");
            return false;
        }
        this.isBusy = true;
        this.currentCycle = new WashCycle(student, this, washType);
        System.out.printf("%s started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), id, student.getName(), washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeCycle() {
        if (isBusy) {
            this.isBusy = false;
            this.currentCycle = null;
            System.out.println(id + " cycle completed. " + id + " is now free.");
        }
    }
}

class WashCycle {
    private final Student student;
    private final WashingMachine machine;
    private final WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        // Asha starts a Quick wash on Machine M1
        m1.startWash(asha, new QuickWash());

        // Ravi attempts to start a Heavy wash on Machine M1
        m1.startWash(ravi, new HeavyWash());

        // Ravi starts a Heavy wash on Machine M2
        m2.startWash(ravi, new HeavyWash());

        // Machine M1 completes its cycle
        m1.completeCycle();

        // Neha starts a Normal wash on Machine M1
        m1.startWash(neha, new NormalWash());
    }
}