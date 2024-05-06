package model;

public record LottoNumber(int number) implements Comparable<LottoNumber> {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    public LottoNumber {
        validateRange(number);
    }

    public static LottoNumber valueOf(String number) {
        try {
            return new LottoNumber(Integer.parseInt(number));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 합니다.");
        }
    }

    private void validateRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(number, other.number);
    }
}
