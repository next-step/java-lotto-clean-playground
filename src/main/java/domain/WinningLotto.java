package domain;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank determineRank(Lotto lotto) {
        int matchCount = winningNumbers.countMatchingNumbers(lotto);
        boolean bonusMatched = lotto.containsNumber(bonusNumber);

        return LottoRank.from(matchCount, bonusMatched);
    }

    private void validateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
