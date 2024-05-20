package domain;

import java.util.Arrays;

public enum Rank {
    FIFTH_PRIZE(3, 5000, true, "3개 일치 (5000원)- "),
    FOURTH_PRIZE(4, 50000, true, "4개 일치 (50000원)- "),
    THIRD_PRIZE(5, 1500000, true, "5개 일치 (1500000원)- "),
    SECOND_PRIZE(5, 30000000, false, "5개 일치, 보너스 볼 일치(30000000원) - "),
    FIRST_PRIZE(6, 2000000000, true, "6개 일치 (2000000000원)- "),
    ;

    private final int matchingCount;
    private final int rankMoney;
    private final boolean hasBonusBall;
    private final String message;

    Rank(int matchingCount,
         int rankMoney,
         boolean hasBonusBall,
         String message) {
        this.matchingCount = matchingCount;
        this.rankMoney = rankMoney;
        this.hasBonusBall = hasBonusBall;
        this.message = message;
    }

    public static Rank of(int matchingCount, boolean hasBonusBall) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchingCount == matchingCount)
                .filter(rank -> rank.hasBonusBall == hasBonusBall)
                .findFirst()
                .orElse(null);
    }


    public int getMatchingCount() {
        return matchingCount;
    }

    public int getRankMoney() {
        return rankMoney;
    }

    public String getMessage() {
        return message;
    }
}
