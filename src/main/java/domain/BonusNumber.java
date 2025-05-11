package domain;

public class BonusNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String ERROR_OUT_OF_RANGE = "[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.";
    private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final int value;

    public BonusNumber(int value, WinningNumbers winningNumbers) {
        validateRange(value);
        validateDuplicate(value, winningNumbers);
        this.value = value;
    }

    public int value() {
        return value;
    }

    private void validateRange(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
    }

    private void validateDuplicate(int value, WinningNumbers winningNumbers) {
        boolean isDuplicate = winningNumbers.getNumbers().stream()
                .anyMatch(winningNumber -> winningNumber.value() == value);

        if (isDuplicate) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BonusNumber other = (BonusNumber) obj;
        return this.value == other.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
