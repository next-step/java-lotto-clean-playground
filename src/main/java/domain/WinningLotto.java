package domain;

public class WinningLotto {
    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank determineRank(Lotto lotto) {
        int matchCount = winningNumbers.countMatchingNumbers(lotto);
        boolean bonusMatched = isBonusNumberMatched(lotto);

        return LottoRank.from(matchCount, bonusMatched);
    }

    private boolean isBonusNumberMatched(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    private void validateBonusNumber(WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
