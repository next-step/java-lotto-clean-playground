package view;

public class InputValidator {

    public static void validatePurchaseMoneyFormat(String input) {
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자만 입력해야 합니다.");
        }
        validateIntegerOverflow(input);
    }

    public static void validateInputNumberFormat(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 없습니다.");
        }
        if (!input.matches("^[0-9]+(?:, [0-9]+)*$")) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 '숫자, 숫자' 형식으로 쉼표 뒤에 공백을 1개만 포함하여 입력해야 합니다.");
        }
    }

    public static void validateBonusNumberFormat(String input) {
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자만 입력해야 합니다.");
        }
        validateIntegerOverflow(input);
    }

    public static void validateManualLottoNumberTrialCount(String input) {
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 수동 횟수 번호엔 숫자만 있어야 합니다.");
        }
        validateIntegerOverflow(input);
    }

    private static void validateIntegerOverflow(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정상적인 숫자 범위를 초과했습니다.");
        }
    }
}