package domain;

import java.util.List;

public class LottoNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 번호는 1부터 45 사이여야 합니다.";
    private static final String ERROR_DUPLICATE = "[ERROR] 번호는 당첨 번호와 중복될 수 없습니다.";

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static void validateBonusNumber(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        for (LottoNumber number : winningNumbers) {
            if (number.value() == bonusNumber.value()) {
                throw new IllegalArgumentException(ERROR_DUPLICATE);
            }
        }
    }

    private void validateRange(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        LottoNumber other = (LottoNumber) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
