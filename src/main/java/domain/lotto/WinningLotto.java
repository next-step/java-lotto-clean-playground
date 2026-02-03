package domain.lotto;

import exception.BonusNumberDuplicatedException;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new BonusNumberDuplicatedException();
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = winningNumbers.countMatchingNumbers(lotto);
        boolean matchBonus = lotto.contains(bonusNumber);
        return Rank.valueOf(matchCount, matchBonus);
    }
}
