package exception;

import java.util.ArrayList;

public record BonusNumber(ArrayList<Integer> winningNumbers, int bonusNumber) {

    public BonusNumber {
        validateDuplication(winningNumbers);
    }

    private static void validateDuplication(ArrayList<Integer> winningNumbers) {
        if (winningNumbers.stream().distinct().count() != 7) {
            throw new IllegalArgumentException("숫자는 중복 불가능합니다.");
        }
    }
}
