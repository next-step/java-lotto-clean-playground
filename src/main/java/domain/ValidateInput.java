package domain;

public class ValidateInput {
    public static void validateNumber(String input) {
        if (isNotNumeric(input)) {
            throw new IllegalArgumentException("입력값은 숫자여야 합니다.");
        }
    }

    public static void validateEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
    }

    public static void validateLotto(String input) {
        String[] tokens = input.split(",");
        for (String token : tokens) {
            if (isNotNumeric(token.trim())) {
                throw new IllegalArgumentException("로또 번호는 숫자여야 하며, 각 번호는 쉼표(,)로 구분되어야 합니다.");
            }
        }
    }

    public static void validateBonusNumber(Lotto numbers, LottoNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼의 숫자가 지난 주 당첨 번호와 중복됩니다.");
        }
    }

    public static void validateManualCount(int count, int manualCount) {
        if (count < manualCount) {
            throw new IllegalArgumentException("입력하신 수동 구매 횟수가 뽑을 수 있는 로또 수를 넘어섰습니다.");
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
