package week_four.class_problems;

/**
 * M4. One-Time College Setup, Many Students
 *
 * Scenario:
 * SRM's student portal wants the college name and academic year loaded exactly once
 * through a static block.
 *
 * Requirement:
 * - collegeName and academicYear must be set exactly once, through a static block —
 *   never repeated per object.
 * - Creating multiple students in a loop must not cause the static block to run more than once.
 * - Every student created in the batch must print a short confirmation line.
 */
public class SrmStudent {
    static String collegeName;
    static String academicYear;
    String name;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + name);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            new SrmStudent(name);
        }
    }
}