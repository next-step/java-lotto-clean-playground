package enums;

import java.math.BigDecimal;
import java.util.Arrays;

public enum LottoRank {
    NO_MATCH(0,BigDecimal.valueOf(0)),
    MATCH_3(3, BigDecimal.valueOf(5_000)),
    MATCH_4(4, BigDecimal.valueOf(50_000)),
    MATCH_5(5, BigDecimal.valueOf(1_500_000)),
    MATCH_5_BONUS(5,BigDecimal.valueOf( 30_000_000)),
    MATCH_6(6, BigDecimal.valueOf(2_000_000_000));

    private final int matchCount;
    private final BigDecimal prize;

    LottoRank(int matchCount, BigDecimal prize) {
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

    public BigDecimal getPrize() {
        return prize;
    }
}
