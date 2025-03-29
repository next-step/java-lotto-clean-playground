package domain;

public record LottoNumber(int number) implements Comparable<LottoNumber> {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LottoNumber {
        validateNumber(number);
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.number, other.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
