package domain;

import java.util.Arrays;

public enum LottoRank {
    FOURTH(3, 5_000),
    THIRD(4, 50_000),
    SECOND(5, 1_500_00),
    FIRST(6, 2_000_000_000),
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
