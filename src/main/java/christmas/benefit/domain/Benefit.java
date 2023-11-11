package christmas.benefit.domain;

public record Benefit(
        Event event,
        int discountAmount
) {

    public boolean isGiveawayEvent() {
        return event == Event.GIVEAWAY;
    }
}
