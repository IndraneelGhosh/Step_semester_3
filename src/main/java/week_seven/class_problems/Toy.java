package week_seven.class_problems;

/**
 * Problem 1: The Talking Toy Box
 *
 * Requirements:
 * - Toy must be an abstract class with an abstract method makeSound(), and a final toyId
 *   field assigned via a shared static counter inside its constructor.
 * - ToyCar and ToyRobot must both extend Toy directly and implement makeSound() with
 *   genuinely different sounds.
 * - new Toy() cannot compile because Toy is abstract.
 */
public abstract class Toy {
    private static int idCounter = 1000;
    private final String toyId;
    protected String name;

    public Toy(String name) {
        this.name = name;
        this.toyId = "TOY-" + (++idCounter);
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound());

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound());

        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}

class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}