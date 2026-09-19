package week_five.class_problems;

/**
 * MovieTicket model demonstrating four Java access levels:
 * - seatNumber: private (internal to MovieTicket)
 * - screenId: default / package-private (accessible to booking package)
 * - ticketPrice: protected (accessible to subclasses like PremiumMovieTicket)
 * - movieTitle: public (visible to any module/display)
 */
public class MovieTicket {
    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}