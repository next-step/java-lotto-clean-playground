package lotto;

public record PrizeMoney(long value) {

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
