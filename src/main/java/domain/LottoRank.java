package domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    FIFTH_PRIZE(3, 5_000, "3개 일치 (5000원)",false),
    FOURTH_PRIZE(4, 50_000, "4개 일치 (50000원)",false),
    THIRD_PRIZE(5, 1_500_000, "5개 일치 (1500000원)",false),
    SECOND_PRIZE(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30000000원)",true),
    FIRST_PRIZE(6, 2_000_000_000, "6개 일치 (2000000000원)",false);

    private final int matchCount;
    private final int prize;
    private final String message;
    private final boolean isMatched;

    LottoRank(int matchCount, int prize, String message, boolean isMatched) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.message = message;
        this.isMatched = isMatched;
    }

    public static Optional<LottoRank> of(int matchCount,boolean isMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.isMatched == isMatched )
                .findFirst();
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
