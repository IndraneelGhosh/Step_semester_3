package week_three.class_problems;

/**
 * M1. From Parallel Arrays to a Class
 *
 * Scenario:
 * The T&P cell currently tracks student placements using three parallel arrays —
 * names, companies, packages — and it has already gone out of sync twice this month.
 * Rebuild it the OOP way.
 */
public class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    public void printRecord() {
        System.out.printf("%s -> %s @ %.1f LPA%n", studentName, company, packageLpa);
    }

    public static void main(String[] args) {
        PlacementRecord[] records = new PlacementRecord[] {
            new PlacementRecord("Ravi", "TCS", 4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik", "Infosys", 4.0)
        };

        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}