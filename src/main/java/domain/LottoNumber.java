package domain;

public record LottoNumber(int number) {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public LottoNumber {
        validateLottoRange(number);
    }

    public static LottoNumber of(int number) {
        return new LottoNumber(number);
    }

    private void validateLottoRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }
}
