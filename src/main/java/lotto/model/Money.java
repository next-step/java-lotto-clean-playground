package lotto.model;

public class Money {
    private final int money;

    private Money(final int money) {
        this.money = money;
    }

    public static Money from(final String moneyInput) {
        final int money = convertMoney(moneyInput);
        validatePositive(money);

        return new Money(money);
    }

    private static int convertMoney(final String moneyInput) {
        try {
            return Integer.parseInt(moneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아니거나 형식이 잘못됨");
        }
    }

    private static void validatePositive(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("돈은 0보다 커야함");
        }
    }

    public int getMoney() {
        return money;
    }
}
