package week_seven.assignment_problems;

/**
 * Problem 5: Skyline Delivery Fleet
 *
 * Requirements:
 * - Drone must be an abstract class with an abstract method fly().
 * - Define an interface Trackable with a method getLocation().
 * - DeliveryDrone extends Drone and implements Trackable.
 * - ScoutDrone extends Drone but does NOT implement Trackable.
 * - GroundRobot implements only Trackable, with no relationship to Drone.
 * - static String getLocationIfTrackable(Object o) checks with instanceof, casts,
 *   and calls getLocation() — otherwise returns "Tracking not available".
 */
public abstract class Drone {
    protected String id;

    public Drone(String id) {
        this.id = id;
    }

    public abstract String fly();

    public String getId() {
        return id;
    }

    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            return ((Trackable) o).getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Delivery drone " + id + " in flight";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    public ScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Scout drone " + id + " in flight";
    }
}

class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}