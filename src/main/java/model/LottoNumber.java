package model;

public record LottoNumber(int number) implements Comparable<LottoNumber> {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    public LottoNumber {
        validateRange(number);
    }

    private void validateRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Override
    public int compareTo(final LottoNumber other) {
        return Integer.compare(number, other.number);
    }
}