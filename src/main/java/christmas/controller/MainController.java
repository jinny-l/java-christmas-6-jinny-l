package christmas.controller;

import christmas.domain.benfit.Benefits;
import christmas.domain.date.EventDate;
import christmas.domain.order.Orders;
import christmas.domain.plan.Plan;
import christmas.dto.EventDateRequest;
import christmas.repository.EventDateRepository;
import christmas.repository.EventRepository;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class MainController {

    public void run() {
        EventDate eventDate = readEventDate();
        Orders orders = readOrders();
        Plan plan = new Plan(eventDate, orders);

        Benefits benefits = Benefits.from(plan, EventRepository.findAll());

        OutputView.printInfoPrefix(eventDate);
        OutputView.printOrders(orders);
        OutputView.printOrdersTotalValue(orders);
        OutputView.printGiveaways(benefits.getGiveaways(plan));
        OutputView.printBenefits(benefits);
        OutputView.printDiscountValue(benefits);
        OutputView.printFinalValue(orders, benefits);

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
