package domain;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, new Money(2_000_000_000L)),
    SECOND(5, true, new Money(30_000_000L)),
    THIRD(5, false, new Money(1_500_000L)),
    FOURTH(4, false, new Money(50_000L)),
    FIFTH(3, false, new Money(5_000L)),
    NONE(0, false, new Money(0L));

    private final int matchCount;
    private final boolean isBonus;
    private final Money prizeAmount;

    Prize(int matchCount,  boolean isBonus, Money prizeAmount) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.prizeAmount = prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getPrizeAmount() {
        return prizeAmount;
    }

    public static Prize fromMatchCount(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount && prize.isBonus == matchBonus)
                .findFirst()
                .orElseGet(() ->
                        Arrays.stream(values())
                                .filter(prize -> prize.matchCount == matchCount && !prize.isBonus)
                                .findFirst()
                                .orElse(NONE)
                );
    }

    public boolean isWinning() {
        return this != NONE;
    }

}

