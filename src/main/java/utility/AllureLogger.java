package utility;

import io.qameta.allure.Step;

public class AllureLogger {
//    private static final Logger logger = LogManager.getLogger();

    private AllureLogger() {
        // hide default constructor for this util class
    }

    @Step("{0}")
    public static void logToAllure(String message) {
    }

}
