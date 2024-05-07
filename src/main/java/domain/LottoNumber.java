package domain;

import java.util.Random;

public record LottoNumber(int number) implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final Random random = new Random();

    public LottoNumber {
        valdateNumber(number);
    }

    private void valdateNumber(final int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 값이어야 합니다.");
        }
    }

    public static LottoNumber generate() {
        return new LottoNumber(random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(final LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }
}
