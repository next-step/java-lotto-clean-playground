package domain;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonus;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonus) {
        validateBonus(winningNumbers, bonus);
        this.winningNumbers = winningNumbers;
        this.bonus = bonus;
    }

    private void validateBonus(Lotto winningNumbers, LottoNumber bonus) {
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = countMatch(lotto);
        boolean bonusMatched = lotto.contains(bonus);
        return Rank.of(matchCount, bonusMatched);
    }

    private int countMatch(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
