package model;

import java.util.Arrays;

public enum Ranking {

    MISS(0, 0, "낙첨"),                                            // 낙첨
    FIFTH(3, 5_000, "3개 일치 (5000원)- "),                         // 5등
    FOURTH(4, 50_000, "4개 일치 (50000원)- "),                      // 4등
    THIRD(5, 1_500_000, "5개 일치 (1500000원)- "),                  // 3등
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치(30000000원) - "),  // 2등
    FIRST(6, 2_000_000_000, "6개 일치 (2000000000원)- ");           // 1등

    private final int matchingNumbers;
    private final long prize;
    private final String resultMessage;

    Ranking(int matchingNumbers, long prize, String resultMessage) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
        this.resultMessage = resultMessage;
    }

    public static Ranking getRanking(int matchingCount, boolean hasMatchedBonusBall) {
        validateMatchingCount(matchingCount);
        if (matchingCount == 5 && hasMatchedBonusBall) {
            return SECOND;
        }
        if (matchingCount == 5) {
            return THIRD;
        }
        return getRankingByMatcingCount(matchingCount);
    }

    private static void validateMatchingCount(int matchingCount) {
        if (matchingCount < 0 || matchingCount > 6) {
            throw new IllegalArgumentException("당첨번호와 일치하는 숫자의 개수는 최소 0개 최대 6개 입니다!");
        }
    }

    private static Ranking getRankingByMatcingCount(int matchingCount) {
        return Arrays.stream(values())
                .filter(ranking -> ranking.matchingNumbers == matchingCount)
                .findFirst()
                .orElse(MISS);
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    public long getPrize() {
        return prize;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
