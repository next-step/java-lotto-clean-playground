package model;

import java.util.Arrays;

public enum LottoRank {
    THREE_MATCHES(3, false, 5_000),
    FOUR_MATCHES(4, false, 50_000),
    FIVE_MATCHES(5, false, 1_500_000),
    FIVE_MATCHES_BONUS(5, true, 30_000_000),
    SIX_MATCHES(6, false, 2_000_000_000),
    NO_WINNER(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int price;

    LottoRank(int matchCount, boolean matchBonus, int price) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.price = price;
    }

    public static LottoRank getLottoRank(int matchCount, boolean matchBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.isMatchBonus(matchCount, matchBonus))
                .findFirst()
                .orElse(NO_WINNER);
    }

    public int getPrice() {
        return price;
    }

    public int getMatchCount() {
        return matchCount;
    }

    private boolean isMatchBonus(int matchCount, boolean matchBonus) {
        return this.matchCount == matchCount && this.matchBonus == matchBonus;
    }
}
