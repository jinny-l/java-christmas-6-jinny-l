package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.exception.ChristmasPromotionException;
import christmas.view.exception.InputError;
import java.util.List;
import java.util.Map;

public class InputView {

    private InputView() {
    }

    public static String readVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return readLine();
    }

    public static Map<String, Integer> readOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        List<String> input = InputParser.splitToList(",", readLine());

        return InputParser.splitListToMap("-", input);
    }

    private static String readLine() {
        String input = Console.readLine().strip();
        validateHasInput(input);
        return input;
    }

    private static void validateHasInput(String input) {
        if (input.isBlank()) {
            throw new ChristmasPromotionException(InputError.BLANK_INPUT);
        }
    }
}
