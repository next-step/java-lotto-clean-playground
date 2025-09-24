package inputView;

public class Price {
    private final int value;

    public Price(int value) {
        checkNegative(value);
        this.value = value;
    }

    private static void checkNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("구입 금액은 0원 이상입니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public int howManyLottos() {
        return value / 1000;
    }
}
