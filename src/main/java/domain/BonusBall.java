package domain;

import java.util.List;

public class BonusBall {
    private final int bonusNumber;

    public BonusBall(int bonusNumber, List<Integer> winningNumbers) {
        validateRange(bonusNumber);
        validateDuplicate(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateRange(int bonusNumber) {
        if (Lotto.MIN_LOTTO_NUMBER > bonusNumber || Lotto.MAX_LOTTO_NUMBER < bonusNumber) {
            throw new IllegalArgumentException("보너스 볼은 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public boolean isMatch(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
