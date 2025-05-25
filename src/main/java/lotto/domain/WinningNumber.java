package lotto.domain;

import java.util.List;

public class WinningNumber {
    private final List<LottoNumber> winning;
    private final LottoNumber bonus;

    private WinningNumber(List<LottoNumber> winning, LottoNumber bonus) {
        this.bonus = bonus;
        this.winning = winning;
    }

    public static WinningNumber of(List<LottoNumber> winning, LottoNumber bonus) {
        return new WinningNumber(winning, bonus);
    }

    public List<LottoNumber> getWinning() {
        return winning;
    }

    public LottoNumber getBonus() {
        return bonus;
    }

    public boolean contains(LottoNumber number) {
        return winning.contains(number);
    }
}
