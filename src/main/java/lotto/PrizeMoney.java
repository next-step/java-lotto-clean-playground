package lotto;

import java.util.Objects;

public class PrizeMoney {
    private final long money;

    public PrizeMoney(long value) {
        this.money = value;
    }

    public PrizeMoney add(PrizeMoney other) {
        return new PrizeMoney(money + other.money);
    }

    public PrizeMoney multiply(int count) {
        return new PrizeMoney(money * count);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PrizeMoney)) {
            return false;
        }
        PrizeMoney prizeMoney = (PrizeMoney) object;
        return money == prizeMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
