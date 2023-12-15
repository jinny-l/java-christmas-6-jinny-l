package christmas.view;

public class OutputView {

    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.printf("%s%s%n", ERROR_MESSAGE_PREFIX, e.getMessage());
    }

}
