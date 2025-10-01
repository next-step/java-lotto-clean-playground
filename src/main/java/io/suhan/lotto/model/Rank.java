package io.suhan.lotto.model;

import io.suhan.lotto.model.lotto.Lotto;

public enum Rank {
    FIRST(2000000000, "6개 일치"),
    SECOND(30000000, "5개 일치, 보너스 볼 일치"),
    THIRD(1500000, "5개 일치"),
    FOURTH(50000, "4개 일치"),
    FIFTH(5000, "3개 일치"),
    NONE(0, ""); // fallback

    private final int prize;
    private final String description;

    Rank(int prize, String description) {
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
