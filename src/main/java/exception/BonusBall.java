package exception;

public record BonusBall(
        int value
) {
    public static BonusBall from(final String number, final CollectNumber collectNumber) {

        validateIsNotNumber(number);
        validateRange(number);
        validateDuplicate(number, collectNumber);

        return new BonusBall(Integer.parseInt(number));
    }

    private static void validateIsNotNumber(String number) {

        try {
            int num = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력해야 됩니다.");
            throw e;
        }
    }

    private static void validateRange(String number) {

        int num = Integer.parseInt(number);

        if (num < 1 || num > 45) throw new IllegalArgumentException("[ERROR] 로또 범위를 벗어났습니다.");
    }

    private static void validateDuplicate(final String number, final CollectNumber collectNumber) {
        int num = Integer.parseInt(number);

        if (collectNumber.value().contains(num))
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 같을수 없습니다.");
    }
}
