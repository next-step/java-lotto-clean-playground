package domain.winning;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import java.util.List;

public class BonusBall {
    private final LottoNumber bonusNumber;

    public BonusBall(LottoNumber bonusNumber, List<Integer> winningNumbers) {
        validateDuplicateInWinningNumber(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatch(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber.getNumber());
    }

    private void validateDuplicateInWinningNumber(LottoNumber bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber.getNumber())) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
