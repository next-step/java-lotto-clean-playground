package lotto.domain;

public enum Rank {
    //[학습] enum 안의 상수마다 메서드를 다르게 구현할 수 있다.
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000) {
        @Override
        public boolean matches(int matchCount, boolean hasBonus) {
            return getMatchCount() == matchCount && hasBonus; //보너스볼이 true일때만 SECOND로 인정
        }
    }, //얘는 보너스일때
    THIRD(5, 1_500_000) {
        @Override
        public boolean matches(int matchCount, boolean hasBonus) {
            return getMatchCount() == matchCount && !hasBonus; //보너스볼이 false일때만 THIRD로 인정
        }
    }, //보너스 아닌경우
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int matchCount;
    private final int prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public boolean matches(int matchCount, boolean hasBonus) {
        return this.matchCount == matchCount;   // 기본값: "매치 개수만 같으면 나야"
    }

    public static Rank of(int matchCount, boolean hasBonus) {

        if (FIRST.matches(matchCount, hasBonus)) {
            return FIRST;
        }
        if (SECOND.matches(matchCount, hasBonus)) {
            return SECOND;
        }
        if (THIRD.matches(matchCount, hasBonus)) {
            return THIRD;
        }
        if (FOURTH.matches(matchCount, hasBonus)) {
            return FOURTH;
        }
        if (FIFTH.matches(matchCount, hasBonus)) {
            return FIFTH;
        }
        return MISS;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
