package domain;

import java.util.Arrays;

public enum Rank
{
    FIRST(6, false, 2000000000L),
    SECOND(5, true, 30000000L),
    THIRD(5, false, 1500000L),
    FOURTH(4, false, 50000L),
    FIFTH(3, false, 5000L),
    NONE(0, false, 0L);

    private final int matchCount;
    private final boolean requiresBonus;
    private final long prize;

    Rank(int matchCount, boolean requiresBonus, long prize)
    {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public long getPrize()
    {
        return prize;
    }

    // 일치하는 숫자 개수와 2등 보너스번호 일치 여부 확인
    public static Rank calculateMatchCount(int match, boolean bonusMatch)
    {
        if (match == 5 && bonusMatch)
            return SECOND;

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == match && !rank.requiresBonus)
                .findFirst()
                .orElse(NONE);
    }
}
