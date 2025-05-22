package domain;

import java.util.List;

public class WinningLotto {
    private static final String ERROR_DUPLICATED_WITH_BONUS = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private final Lotto lotto;
    private final LottoNumber bonus;

    public WinningLotto(Lotto lotto, LottoNumber bonus) {
        validateBonusNotDuplicated(lotto.getNumbers(), bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public Rank calculateRank(Lotto target) {
        int matchCount = (int) target.getNumbers().stream()
                .filter(lotto.getNumbers()::contains)
                .count();
        boolean bonusMatch = target.getNumbers().contains(bonus);
        return Rank.valueOf(matchCount, bonusMatch);
    }

    public List<LottoNumber> getNumbers() {
        return lotto.getNumbers();
    }

    public LottoNumber getBonus() {
        return bonus;
    }

    private void validateBonusNotDuplicated(List<LottoNumber> winningNumbers, LottoNumber bonus) {
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_WITH_BONUS);
        }
    }
}
