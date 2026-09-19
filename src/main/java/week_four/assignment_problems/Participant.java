package week_four.assignment_problems;

/**
 * A1. Overloaded Constructors for Hackathon Registration
 *
 * Scenario:
 * A hackathon's registration desk stays open all evening. Some participants arrive
 * already part of a team and give their team name at signup; others register solo
 * and get a placeholder team name until they're matched with one later that night.
 *
 * Task:
 * - Define a class Participant with fields String name, String teamName, and boolean registered.
 * - Write Participant(String name, String teamName) that sets all three fields directly, with registered set to true.
 * - Write a second constructor Participant(String name) for solo entries, which uses this(name, "Unassigned") to chain.
 * - Add printStatus() that prints name, teamName, and registered on one line.
 * - Loop over names and teamNames, check if teamNames[i] is empty, call the matching constructor, and call printStatus().
 */
public class Participant {
    String name;
    String teamName;
    boolean registered;

    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    public Participant(String name) {
        this(name, "Unassigned"); // chains via this(...)
    }

    public void printStatus() {
        System.out.println(name + " | " + teamName + " | Registered: " + registered);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teamNames = {"ByteBusters", "", "CodeCrafters", ""};

        for (int i = 0; i < names.length; i++) {
            Participant participant;
            if (teamNames[i] == null || teamNames[i].trim().isEmpty()) {
                participant = new Participant(names[i]);
            } else {
                participant = new Participant(names[i], teamNames[i]);
            }
            participant.printStatus();
        }
    }
}