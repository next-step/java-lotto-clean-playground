package lotto;

import java.util.Objects;

public class PrizeMoney {
    private final long prizeMoney;

    public PrizeMoney(long value) {
        this.prizeMoney = value;
    }

    public PrizeMoney add(PrizeMoney other) {
        return new PrizeMoney(prizeMoney + other.prizeMoney);
    }

    public PrizeMoney multiply(int count) {
        return new PrizeMoney(prizeMoney * count);
    }

    long getPrizeMoney() {
        return prizeMoney;
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
        return this.prizeMoney == prizeMoney.prizeMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeMoney);
    }

    @Override
    public String toString() {
        return String.valueOf(prizeMoney);
    }
}
