package domain.lotto;

public record LottoNumber(int value) {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    public LottoNumber {
        validateNumberRange(value);
    }

    public static void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }
}
