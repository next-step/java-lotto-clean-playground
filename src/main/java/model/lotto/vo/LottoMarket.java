package model.lotto.vo;

import model.lotto.exception.MoneyFormatException;
import model.lotto.exception.MoneyRemainException;

public class LottoMarket {

    private static final int DEFAULT_LOTTO_COST = 1000;

    private final int money;

    private LottoMarket(final int money) {
        this.money = money;
    }

    public static LottoMarket from(final String moneyInput) {
        int money = convertMoney(moneyInput);
        validateMoney(money);

        return new LottoMarket(money);
    }

    private static int convertMoney(final String moneyInput) {
        try {
            return Integer.parseInt(moneyInput);
        } catch (NumberFormatException exception) {
            throw new MoneyFormatException();
        }
    }

    private static void validateMoney(final int money) {
        validateMoneyPositive(money);
        validateMoneyDivideZero(money);
    }

    private static void validateMoneyPositive(final int money) {
        if (money <= 0) {
            throw new MoneyFormatException();
        }
    }

    private static void validateMoneyDivideZero(final int money) {
        if (money % DEFAULT_LOTTO_COST != 0) {
            throw new MoneyRemainException();
        }
    }

    public int calculateBoughtLottoSize() {
        return this.money / DEFAULT_LOTTO_COST;
    }
}
