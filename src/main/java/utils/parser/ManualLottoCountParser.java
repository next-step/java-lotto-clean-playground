package utils.parser;

import domain.Lotto;

public class ManualLottoCountParser {
    private static final String ERROR_INVALID_NUMBER = "[ERROR] 수동 로또 수는 0 이상의 정수여야 합니다.";
    private static final String ERROR_EXCEED_PURCHASE_AMOUNT = "[ERROR] 구입 금액을 초과하여 수동 로또를 구매할 수 없습니다.";

    public static int parse(String input, int purchaseAmount) {
        int manualCount = parseManualCount(input);
        validatePurchaseAmount(manualCount, purchaseAmount);
        return manualCount;
    }

    private static int parseManualCount(String input) {
        int count = tryParseInt(input);
        validateNonNegative(count);
        return count;
    }

    private static int tryParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER);
        }
    }

    private static void validateNonNegative(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(ERROR_INVALID_NUMBER);
        }
    }

    private static void validatePurchaseAmount(int manualCount, int purchaseAmount) {
        int maxCount = purchaseAmount / Lotto.PRICE;
        if (manualCount > maxCount) {
            throw new IllegalArgumentException(ERROR_EXCEED_PURCHASE_AMOUNT);
        }
    }
}
