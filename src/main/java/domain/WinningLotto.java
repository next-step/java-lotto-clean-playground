package domain;

public class WinningLotto {

    private final WinningNumbers winningNumbers;
    private final LottoNumber bonus;

    public WinningLotto(WinningNumbers winningNumbers, LottoNumber bonus) {
        validateBonus(winningNumbers, bonus);
        this.winningNumbers = winningNumbers;
        this.bonus = bonus;
    }

    private void validateBonus(WinningNumbers winningNumbers, LottoNumber bonus) {
        if (winningNumbers.contains(bonus.getValue())) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = winningNumbers.countMatch(lotto);
        boolean bonusMatched = lotto.getNumbers().contains(bonus.getValue());
        return Rank.of(matchCount, bonusMatched);
    }
}
