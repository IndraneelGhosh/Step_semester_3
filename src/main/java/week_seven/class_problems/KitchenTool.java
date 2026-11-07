package week_seven.class_problems;

/**
 * Problem 4: Smart Kitchen Assistant
 *
 * Requirements:
 * - KitchenTool must be an abstract class with an abstract method prepare(), and a private
 *   speedLevel field exposed only through getSpeedLevel()/setSpeedLevel() methods —
 *   setSpeedLevel() must reject any value outside 1-5.
 * - Define an interface Washable with a method clean() returning a String.
 * - Blender must extend KitchenTool and implement Washable.
 */
public abstract class KitchenTool {
    private int speedLevel = 1;

    public KitchenTool() {
    }

    public abstract String prepare();

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        } else {
            System.out.println("rejected, speed level stays " + this.speedLevel);
        }
    }

    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel());

        b.setSpeedLevel(9);

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}

interface Washable {
    String clean();
}

class Blender extends KitchenTool implements Washable {
    public Blender() {
        super();
    }

    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}