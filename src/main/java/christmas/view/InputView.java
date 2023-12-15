package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {

    private InputView() {
    }

    public static String readDay() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        return readLine();
    }

    public static List<List<String>> readOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        List<String> split = List.of(readLine().split(","));
        return split.stream()
                .map(element -> List.of(element.split("-")))
                .toList();
    }

    private static String readLine() {
        return Console.readLine().strip();
    }
}
