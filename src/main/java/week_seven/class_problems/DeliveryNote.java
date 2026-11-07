package week_seven.class_problems;

/**
 * Problem 5: Package Drop-Off Log
 *
 * Requirements:
 * - DeliveryNote must be an abstract class with an abstract confirmDelivery() method,
 *   plus an overloaded confirmDelivery(String signature) — true compile-time polymorphism.
 * - ParcelNote and LetterNote must both extend DeliveryNote directly and implement
 *   confirmDelivery() with genuinely different messages.
 * - Write a static method logAll(DeliveryNote[] notes) that loops through a mixed array
 *   and calls confirmDelivery() on each polymorphically.
 */
public abstract class DeliveryNote {
    protected String trackingId;

    public DeliveryNote(String trackingId) {
        this.trackingId = trackingId;
    }

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }

    public static void logAll(DeliveryNote[] notes) {
        if (notes != null) {
            for (DeliveryNote note : notes) {
                if (note != null) {
                    System.out.println(note.confirmDelivery());
                }
            }
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());

        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p; // upcasting: ParcelNote stored as its parent type
        logAll(new DeliveryNote[]{ ref, new LetterNote("TRK-2") });
    }
}

class ParcelNote extends DeliveryNote {
    public ParcelNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    public LetterNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}