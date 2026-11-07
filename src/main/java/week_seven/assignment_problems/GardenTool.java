package week_seven.assignment_problems;

/**
 * Problem 3: Backyard Toolshed Routine
 *
 * Requirements:
 * - Pruner must extend CuttingTool, and CuttingTool must extend GardenTool — a genuine
 *   multilevel hierarchy three classes deep, with each constructor calling super().
 * - GardenTool must be abstract with an abstract use() method; CuttingTool must override
 *   use() with its own concrete version; Pruner must override use() again.
 * - Each override must call super.use() first and then add its own extra detail.
 */
public abstract class GardenTool {
    public GardenTool() {
    }

    public abstract String use();

    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return "Using the tool in the garden, blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}