package christmas.promotion.dto;

import christmas.promotion.domain.Badge;

public record BadgeResponse(
        String name
) {
    public static BadgeResponse from(Badge badge) {
        return new BadgeResponse(badge.getName());
    }
}
