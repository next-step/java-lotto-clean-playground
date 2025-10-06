package inputView;

import java.util.Objects;

public class Price {
    private final int value;

    public Price(int value) {
        vaildateNegative(value);
        this.value = value;
    }

    private static void vaildateNegative(int value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price money = (Price) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
