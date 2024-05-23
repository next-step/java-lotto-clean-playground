package model;

public record ConsumerMoney(
        int value
) {

    public static ConsumerMoney from(final int money) {

        validateMoney(money);
        return new ConsumerMoney(money);
    }

    private static void validateMoney(final int money) {

        if (money < 0)
            throw new IllegalArgumentException("[ERROR] 투입금액은 음수일 수 없습니다.");

        if (money < 1000)
            throw new IllegalArgumentException("[ERROR] 로또의 최소금액은 1000원입니다.");
    }
}
