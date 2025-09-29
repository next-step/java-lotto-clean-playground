package io.suhan.lotto.model;

import io.suhan.lotto.model.lotto.Lotto;

public enum Rank {
    FIRST(Lotto.LOTTO_SIZE, false, 2000000000, "6개 일치"),
    SECOND(Lotto.LOTTO_SIZE - 1, true, 30000000, "5개 일치, 보너스 볼 일치"),
    THIRD(Lotto.LOTTO_SIZE - 1, false, 1500000, "5개 일치"),
    FOURTH(Lotto.LOTTO_SIZE - 2, false, 50000, "4개 일치"),
    FIFTH(Lotto.LOTTO_SIZE - 3, false, 5000, "3개 일치"),
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
        if (matchedCount == Lotto.LOTTO_SIZE) {
            return Rank.FIRST;
        }

        if (matchedCount == Lotto.LOTTO_SIZE - 1 && bonusMatched) {
            return Rank.SECOND;
        }

        if (matchedCount == Lotto.LOTTO_SIZE - 1) {
            return Rank.THIRD;
        }

        if (matchedCount == Lotto.LOTTO_SIZE - 2) {
            return Rank.FOURTH;
        }

        if (matchedCount == Lotto.LOTTO_SIZE - 3) {
            return Rank.FIFTH;
        }

        return Rank.NONE;
    }

    public int getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }
}
