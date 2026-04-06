package domain;

public class ValidateInput {
    public static void validateMoney(String input) {
        if (isNotNumeric(input)) {
            throw new IllegalArgumentException("구입금액은 숫자여야 합니다.");
        }
    }

    public static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
    }

    public static void validateLotto(String input) {
        String[] tokens = input.split(", ");
        for (String token : tokens) {
            if (isNotNumeric(token.trim())) {
                throw new IllegalArgumentException("로또 번호는 숫자여야 하며, 구분자는 ', '여야 합니다.");
            }
        }
    }

    private static boolean isNotNumeric(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
