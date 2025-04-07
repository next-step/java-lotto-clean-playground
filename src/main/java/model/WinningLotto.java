package model;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        int matchCount = (int) lotto.countMatch(winningNumbers);
        boolean bonusMatched = lotto.getNumbers().contains(bonusNumber);
        return Rank.determine(matchCount, bonusMatched);
    }
}
