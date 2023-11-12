package christmas.global.view;

import christmas.global.util.CollectionsMapper;
import christmas.global.util.io.InputUtil;
import java.util.List;
import java.util.Map;

public class InputView {

    private InputView() {
    }

    public static int readVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return InputUtil.readInt();
    }

    public static Map<String, Integer> readOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        String input = InputUtil.readLine();
        List<String> orders = stripElement(
                CollectionsMapper.splitStringToList(",", input)
        );

        return CollectionsMapper.listToMap("-", orders);
    }

    private static List<String> stripElement(List<String> input) {
        return input.stream()
                .map(String::strip)
                .toList();
    }
}
