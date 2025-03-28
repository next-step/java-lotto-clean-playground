package model;

import java.util.Arrays;

public enum Ranking {

    MISS(0, 0),                 // 낙첨
    FOURTH(3, 5_000),           // 4등
    THIRD(4, 50_000),           // 3등
    SECOND(5, 1_500_000),       // 2등
    FIRST(6, 2_000_000_000);    // 1등

    private final int matchingNumbers;
    private final long prize;

    Ranking(int matchingNumbers, long prize) {
        this.matchingNumbers = matchingNumbers;
        this.prize = prize;
    }

    public static Ranking getRanking(int matchingCount) {
        if (matchingCount < 0 || matchingCount > 6) {
            throw new IllegalArgumentException("당첨번호와 일치하는 숫자의 개수는 최소 0개 최대 6개 입니다!");
        }

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
}
