package christmas.global.view;

import christmas.global.exception.ChristmasPromotionException;
import christmas.global.util.CollectionsMapper;
import christmas.global.view.exception.InputError;
import christmas.order.exception.OrderError;
import java.util.List;
import java.util.Map;

public class InputParser {

    private InputParser() {
    }

    public static Map<String, Integer> splitListToMap(String delimiter, List<String> orders) {
        validateDelimiter(delimiter, orders);
        try {
            return CollectionsMapper.splitListToMap(delimiter, orders);

        } catch (IllegalStateException e) { // Map 변환 시 key가 중복될 경우 발생
            throw new ChristmasPromotionException(OrderError.DUPLICATE_MENU);

            // NumberFormatException: Map 변환 시 value를 String에서 Integer로 변환할 때 int가 아닐 경우 발생
            // ArrayIndexOutOfBoundsException: Map으로 파싱할 때 구분자 뒤의 값이 없을 때 발생
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ChristmasPromotionException(OrderError.INVALID_FORMAT);
        }
    }

    private static void validateDelimiter(String delimiter, List<String> input) {
        if (input.stream().anyMatch(element -> !element.contains(delimiter))) {
            throw new ChristmasPromotionException(InputError.INVALID_ORDERS_FORMAT);
        }
    }

    public static List<String> splitToList(String delimiter, String input) {
        try {
            List<String> orders = stripElement(
                    CollectionsMapper.splitStringToList(delimiter, input)
            );
            validateDelimiter(delimiter, input, orders);
            return orders;
        } catch (IllegalStateException e) {
            throw  new ChristmasPromotionException(InputError.INVALID_ORDERS_FORMAT);
        }
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
}
