public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int value;

    LottoNumber(int value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(int value) {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 숫자 범위가 유효하지 않습니다.");
        }
    }
}
