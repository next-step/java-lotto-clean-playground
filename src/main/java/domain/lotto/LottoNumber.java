package domain.lotto;

import java.util.Objects;

public class LottoNumber {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private void validateRange(int number) {
        if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
