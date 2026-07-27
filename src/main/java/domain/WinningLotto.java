package domain;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto from(List<Integer> winningNumbers, int bonusNumber) {
        return new WinningLotto(Lotto.from(winningNumbers), new LottoNumber(bonusNumber));
    }

    public int countMatchingNumbersOf(Lotto purchasedLotto) {
        return purchasedLotto.countMatchingNumbers(lotto);
    }

    public boolean matchesBonus(Lotto purchasedLotto) {
        return purchasedLotto.contains(bonusNumber);
    }

    private void validateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
