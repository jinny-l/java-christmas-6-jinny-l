package christmas.benefit.domain;

public record Benefit(
        Event event,
        int discountValue
) {

    public boolean isGiveawayEvent() {
        return event == Event.GIVEAWAY;
    }

    public boolean isZeroDiscount() {
        return discountValue == 0;
    }
}
