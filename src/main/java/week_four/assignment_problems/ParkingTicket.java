package week_four.assignment_problems;

/**
 * A3. final Method — Parking Overstay Fine Calculator
 *
 * Scenario:
 * Campus parking issues a fine for every vehicle that overstays its allotted free time —
 * but the fine formula must behave identically for every ticket, with no risk of overriding.
 *
 * Task:
 * - Define a class ParkingTicket with fields String vehicleNo and double ratePerMinute.
 * - Add a final method calculateFine(int overstayMinutes) returning overstayMinutes * ratePerMinute.
 * - Add a final method printReceipt(int overstayMinutes) printing vehicleNo and the computed fine.
 * - Loop with if-else, call printReceipt() only if overstayMinutes > 0; otherwise print "<vehicleNo> - No fine, within allotted time".
 */
public class ParkingTicket {
    String vehicleNo;
    double ratePerMinute;

    public ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    public final double calculateFine(int overstayMinutes) {
        return overstayMinutes * ratePerMinute;
    }

    public final void printReceipt(int overstayMinutes) {
        System.out.println(vehicleNo + " - Fine: Rs " + calculateFine(overstayMinutes));
    }

    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"};
        double[] ratePerMinute = {2, 2, 3, 2};
        int[] overstayMinutes = {15, 0, -5, 8};

        for (int i = 0; i < vehicleNos.length; i++) {
            ParkingTicket ticket = new ParkingTicket(vehicleNos[i], ratePerMinute[i]);
            if (overstayMinutes[i] > 0) {
                ticket.printReceipt(overstayMinutes[i]);
            } else {
                System.out.println(vehicleNos[i] + " - No fine, within allotted time");
            }
        }
    }
}