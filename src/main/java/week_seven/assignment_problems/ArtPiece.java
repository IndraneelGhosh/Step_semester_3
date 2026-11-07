package week_seven.assignment_problems;

/**
 * Problem 2: Gallery Description Cards
 *
 * Requirements:
 * - ArtPiece must be an abstract class with an abstract method describe(), and a final
 *   pieceId field assigned via a shared static counter inside its constructor.
 * - Painting and Sculpture must both extend ArtPiece directly and implement describe()
 *   with genuinely different messages.
 */
public abstract class ArtPiece {
    private static int counter = 0;
    private final String pieceId;
    protected String title;

    public ArtPiece(String title) {
        this.title = title;
        this.pieceId = "ART-" + (++counter);
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }

    public String getTitle() {
        return title;
    }

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
    }
}

class Painting extends ArtPiece {
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}