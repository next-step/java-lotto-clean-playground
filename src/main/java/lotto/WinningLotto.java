package lotto;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank determineRank(Lotto lotto) {
        LottoMatch match = match(lotto);
        return LottoRank.from(match.matchCount(), match.bonusMatched());
    }

    private LottoMatch match(Lotto lotto) {
        return new LottoMatch(
                lotto.countMatches(winningNumbers),
                lotto.contains(bonusNumber)
        );
    }

    private void validateBonusNumber(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private record LottoMatch(int matchCount, boolean bonusMatched) {
    }
}
