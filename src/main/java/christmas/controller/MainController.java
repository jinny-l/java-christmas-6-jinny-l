package christmas.controller;

import christmas.domain.EventDate;
import christmas.dto.EventDateRequest;
import christmas.repository.EventDateRepository;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    public void run() {
        EventDate eventDate = readEventDate();
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
}
