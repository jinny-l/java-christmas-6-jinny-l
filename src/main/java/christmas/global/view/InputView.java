package christmas.global.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.exception.ChristmasPromotionException;
import christmas.global.util.CollectionsMapper;
import christmas.global.view.exception.InputError;
import christmas.order.exception.OrderError;
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

        String input = readLine();
        List<String> orders = splitToList(",", input);

        return splitListToMap("-", orders);
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

    private static List<String> splitToList(String delimiter, String input) {
        List<String> orders = stripElement(
                CollectionsMapper.splitStringToList(delimiter, input)
        );
        validateDelimiter(delimiter, input, orders);
        return orders;
    }

    private static List<String> stripElement(List<String> input) {
        return input.stream()
                .map(String::strip)
                .toList();
    }

    private static void validateDelimiter(String delimiter, String input, List<String> orders) {
        if (orders.size() != 1 && !input.contains(delimiter)) {
            throw new ChristmasPromotionException(InputError.INVALID_ORDERS_FORMAT);
        }
    }

    private static Map<String, Integer> splitListToMap(String delimiter, List<String> orders) {
        validateDelimiter(delimiter, orders);
        try {
            return CollectionsMapper.splitListToMap(delimiter, orders);

        } catch (IllegalStateException e) { // Map 변환 시 key가 중복될 경우 발생
            throw new ChristmasPromotionException(OrderError.DUPLICATE_MENU);

        } catch (NumberFormatException e) { // Map 변환 시 value를 String에서 Integer로 변환할 때 int가 아닐 경우 발생
            throw new ChristmasPromotionException(OrderError.INVALID_FORMAT);
        }
    }

    private static void validateDelimiter(String delimiter, List<String> input) {
        if (input.stream().anyMatch(element -> !element.contains(delimiter))) {
            throw new ChristmasPromotionException(InputError.INVALID_ORDERS_FORMAT);
        }
    }
}
