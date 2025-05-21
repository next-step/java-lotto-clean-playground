package domain;

import java.util.Objects;

public record LottoNumber(int number) {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;

    public LottoNumber {
        validateInRange(number);
    }

    private void validateInRange(int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
