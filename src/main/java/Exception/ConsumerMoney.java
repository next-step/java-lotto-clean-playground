package Exception;

import model.Lotto;

public record ConsumerMoney(
        int value
) {

    public static ConsumerMoney from(final String money) {

        validateText(money);
        validateMoney(money);
        return new ConsumerMoney(Integer.parseInt(money));
    }

    private static void validateText(final String money) {

        try {
            int inputMoney = Integer.parseInt(money);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력 가능합니다.");
            throw e;
        }

    }

    private static void validateMoney(final String money) {

        int inputMoney = Integer.parseInt(money);

        if (inputMoney < 0)
            throw new IllegalArgumentException("[ERROR] 투입금액은 음수일 수 없습니다.");

        if (inputMoney < 1000)
            throw new IllegalArgumentException("[ERROR] 로또의 최소금액은 1000원입니다.");

        if (inputMoney % Lotto.getPrice() != 0)
            throw new IllegalArgumentException("[ERROR] 로또를 사고 남은 금액은 없어야 됩니다.");
    }
}
