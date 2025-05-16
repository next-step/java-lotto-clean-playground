package utils.parser;

public class LottoPurchaseAmountParser {

    private static final String ERROR_INVALID_INPUT = "[ERROR] 구입 금액은 숫자로만 입력해야 합니다.";
    private static final String ERROR_NULL_INPUT = "[ERROR] 구입 금액은 null일 수 없습니다.";

    public static int parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ERROR_NULL_INPUT);
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_INPUT);
        }
    }
}
