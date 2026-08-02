package lotto;

public record PrizeMoney(long value) {

    public PrizeMoney {
        if (value < 0) {
            throw new IllegalArgumentException("상금은 음수일 수 없습니다.");
        }
    }

    public PrizeMoney add(PrizeMoney other) {
        return new PrizeMoney(value + other.value);
    }

    public PrizeMoney multiply(int count) {
        return new PrizeMoney(value * count);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
