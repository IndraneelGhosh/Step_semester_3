package week_seven.class_problems;

/**
 * Problem 3: Orchestra Warm-Up Routine
 *
 * Requirements:
 * - Violin must extend StringInstrument, and StringInstrument must extend Instrument —
 *   a genuine multilevel hierarchy three classes deep, with each constructor calling super().
 * - Instrument must be abstract with an abstract play() method; StringInstrument must
 *   override play() with its own concrete version; Violin must override play() again.
 * - Each override must call super.play() first and then add its own extra detail.
 */
public abstract class Instrument {
    public Instrument() {
    }

    public abstract String play();

    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        System.out.println(s.play());

        Violin v = new Violin();
        System.out.println(v.play());
    }
}

class StringInstrument extends Instrument {
    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {
    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}