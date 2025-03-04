package domain;

import java.util.*;

public enum WinningRank {
    THREE_MATCH(3, 5_000, false),
    FOUR_MATCH(4, 50_000, false),
    FIVE_MATCH(5, 1_500_000, false),
    FIVE_MATCH_WITH_BONUS(5, 30_000_000, true),
    SIX_MATCH(6, 2_000_000_000, false);

    private final int matchCount;
    private final int price;
    private final boolean bonusRequired;

    WinningRank(int matchCount, int price, boolean bonusRequired) {
        this.matchCount = matchCount;
        this.price = price;
        this.bonusRequired = bonusRequired;
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static Optional<WinningRank> valueOf(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.bonusRequired == bonusMatch)
                .findFirst();
    }

    public static List<WinningRank> getOrderedRanks() {
        return List.of(
                THREE_MATCH,
                FOUR_MATCH,
                FIVE_MATCH,
                FIVE_MATCH_WITH_BONUS,
                SIX_MATCH
        );
    }
}

