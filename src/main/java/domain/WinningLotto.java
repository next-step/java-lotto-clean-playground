package domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final BonusBall bonusBall;

    public WinningLotto(Lotto winningLotto, BonusBall bonusBall) {
        validateWinningLotto(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.countMatch(this.winningLotto);
        boolean bonusBallMatch = lotto.contains(bonusBall.number());
        return Rank.from(matchCount, bonusBallMatch);
    }

    private void validateWinningLotto(Lotto winningLotto, BonusBall bonusBall) {
        if (winningLotto.contains(bonusBall.number())) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
