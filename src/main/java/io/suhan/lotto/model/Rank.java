package io.suhan.lotto.model;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2000000000, "6개 일치"),
    SECOND(5, true, 30000000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1500000, "5개 일치"),
    FOURTH(4, false, 50000, "4개 일치"),
    FIFTH(3, false, 5000, "3개 일치"),
    NONE(0, false, 0, ""); // fallback

    private final int matchedCount;
    private final boolean bonusRequired;
    private final int prize;
    private final String description;

    Rank(int matchedCount, boolean bonusRequired, int prize, String description) {
        this.matchedCount = matchedCount;
        this.bonusRequired = bonusRequired;
        this.prize = prize;
        this.description = description;
    }

    public static Rank of(int matchedCount, boolean bonusMatched) {
        return Arrays.stream(Rank.values())
                .filter((rank) -> rank.getMatchedCount() == matchedCount)
                .filter((rank) -> rank.isBonusRequired() == bonusMatched)
                .findFirst()
                .orElse(Rank.NONE);
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean isBonusRequired() {
        return bonusRequired;
    }

    public int getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }
}
