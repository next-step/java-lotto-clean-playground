package model;

import java.util.List;

public class WinningNumbers {
    private static final int SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        validate(numbers, bonusNumber);
        this.numbers = List.copyOf(numbers);
        this.bonusNumber = bonusNumber;
    }

    public void validate(List<Integer> numbers, int bonusNumber) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateNumberRange(numbers);
        validateBonusNumber(bonusNumber);
        validateBonusNotInWinningNumbers(numbers, bonusNumber);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("당첨 번호는 6개의 숫자로 입력해야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != SIZE) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(n -> n < MIN_NUMBER || n > MAX_NUMBER)) {
            throw new IllegalArgumentException("당첨 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("보너스 볼은 1부터 45 사이여야 합니다.");
        }
    }

    private void validateBonusNotInWinningNumbers(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }


    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
