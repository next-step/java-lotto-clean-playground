package lotto.model;

import static lotto.global.Constants.LOTTO_COST;

public class LottoMarket {
    private final int money;

    private LottoMarket(final int money) {
        this.money = money;
    }

    public static LottoMarket from(final String moneyInput) {
        int money = convertMoney(moneyInput);
        validate(money);

        return new LottoMarket(money);
    }

    private static int convertMoney(final String moneyInput) {
        try {
            return Integer.parseInt(moneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("돈이 아니거나 형식이 잘못됨");
        }
    }

    private static void validate(final int money) {
        validatePositive(money);
        validateDivideZero(money);
    }

    private static void validatePositive(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("돈은 0보다 커야함");
        }
    }

    private static void validateDivideZero(final int money) {
        if (money % LOTTO_COST != 0) {
            throw new IllegalArgumentException("돈은 " + LOTTO_COST + "의 배수여야 함");
        }
    }

    public int getLottoSize() {
        return this.money / LOTTO_COST;
    }
}
