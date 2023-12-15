package christmas.controller;

import christmas.domain.EventDate;
import christmas.domain.Orders;
import christmas.dto.EventDateRequest;
import christmas.repository.EventDateRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class MainController {

    public void run() {
        EventDate eventDate = readEventDate();
        Orders orders = readOrders();
    }

    private EventDate readEventDate() {
        try {
            EventDateRequest request = EventDateRequest.from(InputView.readDay());
            return EventDateRepository.findBy(request.day());

        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readEventDate();
        }
    }

    private Orders readOrders() {
        try {
            List<List<String>> input = InputView.readOrders();
            return Orders.from(input);

        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readOrders();
        }
    }
}
