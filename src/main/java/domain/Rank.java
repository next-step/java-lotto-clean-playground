package domain;

public enum Rank {

    FIRST(6, 2000000000), // 1등, 6개 정답
    SECOND(5,30000000), // 2등, 5개 정답 & 보너스볼 정답
    THIRD(5, 1500000), // 3등, 5개 정답
    FOURTH(4, 50000), // 4등. 4개 정답
    FIFTH(3, 5000), // 5등, 3개 정답
    MISS(0, 0); // 2개 이하 정답

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }


    public static Rank findRank(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }

        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }

        if (matchCount == 5) {
            return THIRD;
        }

        if (matchCount == 4) {
            return FOURTH;
        }

        if (matchCount == 3) {
            return FIFTH;
        }

        return MISS;
    }


    public int getMatchCount() {
        return matchCount;
    }


    public long getPrize() {
        return prize;
    }
}
