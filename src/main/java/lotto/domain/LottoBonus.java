package lotto.domain;

import java.util.Comparator;
import java.util.EnumSet;

public enum LottoBonus {
    FIRST_PLACE(6, false, 2000000000),
    SECOND_PLACE(5, true, 30000000),
    THIRD_PLACE(5, false, 1500000),
    FOURTH_PLACE(4, false, 50000),
    FIFTH_PLACE(3, false, 5000),
    LOSING_PLACE(0, false, 0);

    private final int matchCount;
    private final boolean needBonusMatch;
    private final Money winningMoney;

    LottoBonus(int matchCount, boolean needBonusMatch, int winningMoney) {
        this.matchCount = matchCount;
        this.needBonusMatch = needBonusMatch;
        this.winningMoney = new Money(winningMoney);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isNeedBonusMatch() {
        return needBonusMatch;
    }

    public Money getWinningMoney() {
        return winningMoney;
    }

    public static LottoBonus valueOf (int matchCount, boolean needBonusMatch) {
        return EnumSet.allOf(LottoBonus.class).stream()
                .filter(b -> b.matchCount == matchCount)
                .min(Comparator.comparing(b -> b.needBonusMatch != needBonusMatch))
                .orElse(LOSING_PLACE);
    }
}
