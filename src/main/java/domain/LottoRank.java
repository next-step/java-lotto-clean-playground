package domain;

import java.util.Arrays;

public enum LottoRank {
    THREE_MATCHED(3, 5_000, false),
    FOUR_MATCHED(4, 50_000, false),
    FIVE_MATCHED(5, 1_500_000, false),
    BONUS_FIVE_MATCHED(5, 30_000_000, true),
    SIX_MATCHED(6, 2_000_000_000, false),
    MISS(0, 0, false);

    private final int matchCount;
    private final long price;
    private final boolean matchBonus;

    LottoRank(int matchCount, long price, boolean matchBonus) {
        this.matchCount = matchCount;
        this.price = price;
        this.matchBonus = matchBonus;
    }

    public static LottoRank getLottoRank(int count, boolean matchBonus) {
        if (count == BONUS_FIVE_MATCHED.matchCount && matchBonus) {
            return BONUS_FIVE_MATCHED;
        }
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchCount == count)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getPrice() {
        return price;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }
}
