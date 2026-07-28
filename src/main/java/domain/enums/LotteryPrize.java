package domain.enums;

import domain.lotto.wrap.Money;

import java.util.Arrays;

public enum LotteryPrize {

    FIRST(6, false, new Money(2_000_000_000)),
    SECOND(5, true, new Money(30_000_000)),
    THIRD(5,false, new Money(1_500_000)),
    FOURTH(4,false, new Money(50_000)),
    FIFTH(3, false, new Money(5_000)),
    MISS(0, false, new Money(0))
    ;

    private final int matchCount;
    private final boolean isMatchedBonus;
    private final Money prize;

    LotteryPrize(int matchCount, boolean isMatchedBonus, Money prize) {
        this.matchCount = matchCount;
        this.isMatchedBonus = isMatchedBonus;
        this.prize = prize;
    }

    public static LotteryPrize of(int matchCount, boolean isMatchedBonus) {
        if (matchCount == 5 && isMatchedBonus) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount && !prize.isMatchedBonus)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getPrize() {
        return prize;
    }

}
