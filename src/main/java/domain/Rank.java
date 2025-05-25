package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000, "6개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    FIFTH(3, false, 5_000, "3개 일치"),
    NONE(0, false, 0, "미당첨");

    private final int matchCount;
    private final boolean matchBonus;
    private final long prize;
    private final String description;

    Rank(int matchCount, boolean matchBonus, long prize, String description) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.matchBonus == matchBonus)
                .findFirst()
                .orElse(NONE);
    }

    public boolean isWinning() {
        return this != NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public long getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }

    public String getPrizeText() {
        if (prize == 0) {
            return "0원";
        }
        return String.format("%,d원", prize);
    }
}
