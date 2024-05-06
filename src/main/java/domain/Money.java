package domain;

public class Money {
    private final int money;
    public static final int PRICE_UNIT = 1_000;

    public Money(String money) {
        this.money = validate(money);
    }

    private int validate(String money) {
        int IntMoney = validateInteger(money);
        validateRange(IntMoney);
        validateUnit(IntMoney);
        return IntMoney;
    }

    public static int validateInteger(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("금액은 숫자형식이여야 합니다.");
        }
    }

    private void validateRange(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("금액은 0보다 큰값이여야 합니다.");
        }
    }

    private void validateUnit(int price) {
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위여야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }

    public int ticket() {
        return money / 1000;
    }
}
