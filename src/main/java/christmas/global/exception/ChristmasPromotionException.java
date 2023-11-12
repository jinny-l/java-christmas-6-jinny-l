package christmas.global.exception;

public class ChristmasPromotionException extends IllegalArgumentException {

    private final Error error;

    public ChristmasPromotionException(Error error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getMessage();
    }
}
