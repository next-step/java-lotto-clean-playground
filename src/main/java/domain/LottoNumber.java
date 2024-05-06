package domain;

import java.util.Random;

public record LottoNumber(int value) implements Comparable<LottoNumber> {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;

    public LottoNumber {
        validateValue(value);
    }

    private void validateValue(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 값이어야 합니다.");
        }
    }

    public static LottoNumber generate() {
        Random random = new Random();
        return new LottoNumber(random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }
}
