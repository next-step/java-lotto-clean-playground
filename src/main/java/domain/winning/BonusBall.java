package domain.winning;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import java.util.List;

public class BonusBall {
    private final LottoNumber bonusNumber;

    public BonusBall(LottoNumber bonusNumber, WinningLotto winningLotto) {
        validateDuplicate(bonusNumber, winningLotto);
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatch(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    private void validateDuplicate(LottoNumber bonusNumber, WinningLotto winningLotto) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
