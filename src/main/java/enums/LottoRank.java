package enums;

import java.util.Arrays;

public enum LottoRank {
    NO_MATCH(0,0),
    MATCH_3(3, 5_000),
    MATCH_4(4, 50_000),
    MATCH_5(5, 1_500_000),
    MATCH_5_BONUS(5, 30_000_000),
    MATCH_6(6, 2_000_000_000);

    private final int matchCount;
    private final int prize;

    LottoRank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoRank determineRank(int matchCount, boolean bonusMatch) {
        if (matchCount == 5 && bonusMatch) {
            return MATCH_5_BONUS;
        }
        
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(NO_MATCH);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
