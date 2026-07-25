package domain.money;

public class PrizeAmount {
    private final long value;

    private PrizeAmount(long value) {
        this.value = value;
    }

    public static PrizeAmount from(long value) {
        return new PrizeAmount(value);
    }

    public PrizeAmount multiply(int count) {
        return new PrizeAmount(value * count);
    }

    public PrizeAmount plus(PrizeAmount other) {
        return new PrizeAmount(value + other.value);
    }

    public long value() {
        return value;
    }
}
