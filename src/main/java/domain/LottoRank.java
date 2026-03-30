package domain;

import java.util.Arrays;

public enum LottoRank {
    THREE_MATCHED(3, 5_000),
    FOUR_MATCHED(4, 50_000),
    FIVE_MATCHED(5, 1_500_00),
    SIX_MATCHED(6, 2_000_000_000),
    MISS(0, 0);

    private final int matchCount;
    private final int price;

    LottoRank(int matchCount, int price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public static LottoRank getLottoRank(int count) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchCount == count)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }
}
