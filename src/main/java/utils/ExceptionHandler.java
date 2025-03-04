package utils;

import org.slf4j.*;

public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void handleException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            System.err.println("입력 오류 : " + e.getMessage());
            logger.warn("입력 오류 : {}", e.getMessage());
        } else {
            System.err.println("시스템 오류 : " + e.getMessage());
            logger.error("시스템 오류 : ", e);
        }
    }
}
