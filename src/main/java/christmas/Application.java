package christmas;

import christmas.global.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        ChristmasPromotionController controller = new ChristmasPromotionController(
                AppConfig.getInstance()
        );
        controller.run();
    }
}
