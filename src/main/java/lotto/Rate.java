package lotto;

public record Rate(float value) {
    @Override
    public String toString() {
        return String.format("%.2f", value);
    }
}
