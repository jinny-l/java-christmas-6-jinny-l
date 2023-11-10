package christmas.global.util.io;

import camp.nextstep.edu.missionutils.Console;

public class InputUtil {

    private InputUtil() {
    }

    public static int readInt() {
        return parseToInteger(readLine());
    }

    private static int parseToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static String readLine() {
        String input = Console.readLine().strip();
        validateHasInput(input);
        return input;
    }

    private static void validateHasInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
