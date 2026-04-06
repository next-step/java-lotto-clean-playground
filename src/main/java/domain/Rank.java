package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    NONE(0, 0),
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int matchCount;
    private final int prizeMoney;

    Rank(int matchCount, int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static Rank valueOf(int matchCount, boolean matchBonus) {
        if (matchCount == 5 && matchBonus) {
            return FIVE_BONUS;
        }
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank != FIVE_BONUS)
                .findFirst()
                .orElse(NONE);
    }

    public static List<Rank> getWinningRanks() {
        return Arrays.stream(values())
                .filter(rank -> rank != NONE)
                .collect(Collectors.toList());
    }

    public String getMessage() {
        if (this == FIVE_BONUS) {
            return matchCount + "개 일치, 보너스 볼 일치 (" + prizeMoney + "원)- ";
        }
        return matchCount + "개 일치 (" + prizeMoney + "원)- ";
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
