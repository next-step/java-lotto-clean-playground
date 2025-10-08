package domain;

import domain.value.BonusNumber;
import domain.value.WinningNumbers;

import java.util.*;

public class Lotto {
    public static final int LOTTO_NUMBER_SIZE = 6;
    public static final int ALL_LOTTO_NUMBER_SIZE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean isBonusNumberMatched(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.value());
    }

    public int countMatches(WinningNumbers winningNumbers) {
        int count = 0;
        for (int singleTargetNumber : winningNumbers.values())
            if (numbers.contains(singleTargetNumber)) count++;
        return count;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(numbers).size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
        }
        for (int n : numbers) {
            if (n < 1 || n > ALL_LOTTO_NUMBER_SIZE) {
                throw new IllegalArgumentException("번호는 1이상 45이하 범위여야 합니다.");
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
