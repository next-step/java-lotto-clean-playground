package domain;

public class WinningLotto {
    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private final Numbers numbers;
    private final Number bonusNumber;

    public WinningLotto(Numbers numbers, Number bonusNumber) {
        validateBonusNumber(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Numbers numbers, Number bonusNumber) {
        if (numbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
    }

    public int countMatch(Numbers other) {
        return (int) other.getNumbers().stream()
                .filter(numbers.getNumbers()::contains)
                .count();
    }

    public boolean matchBonus(Numbers other) {
        return other.getNumbers().stream()
                .anyMatch(num -> num.equals(bonusNumber));
    }

    public Numbers getNumbers() {
        return numbers;
    }
}
