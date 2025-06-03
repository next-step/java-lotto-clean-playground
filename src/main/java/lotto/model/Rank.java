package lotto.model;

import java.util.Arrays;

public enum Rank {
    NONE(0, new Prize(0), false, "낙첨"),
    FIFTH(3, new Prize(5_000), false, "3개 일치 (5000원)"),
    FOURTH(4, new Prize(50_000), false, "4개 일치 (50000원)"),
    THIRD(5, new Prize(1_500_000), false, "5개 일치 (1500000원)"),
    SECOND(5, new Prize(30_000_000), true, "5개 일치, 보너스 볼 일치(30000000원)"),
    FIRST(6, new Prize(2_000_000_000), false, "6개 일치 (2000000000원)");

    private final int matchCount;
    private final Prize prize;
    private final boolean isBonus;
    private final String display;

    Rank(int matchCount, Prize prize, boolean isBonus, String display) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.isBonus = isBonus;
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public Prize getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public static Rank from(int count, boolean isBonus) {
        return Arrays.stream(values())
            .filter(rank -> rank.matchCount == count)
            .filter(rank -> rank.matchCount != 5 || rank.isBonus == isBonus)
            .findFirst()
            .orElse(NONE);
    }
}
