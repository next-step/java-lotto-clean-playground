package domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    FIFTH_PRIZE(3, 5_000, "3개 일치 (5000원)"),
    FOURTH_PRIZE(4, 50_000, "4개 일치 (50000원)"),
    THIRD_PRIZE(5, 1_500_000, "5개 일치 (1500000원)"),
    SECOND_PRIZE(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30000000원)"),
    FIRST_PRIZE(6, 2_000_000_000, "6개 일치 (2000000000원)");

    private final int matchCount;
    private final int prize;
    private final String message;

    LottoRank(int matchCount, int prize, String message) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.message = message;
    }

    public static Optional<LottoRank> of(int matchCount, boolean isBonusNumberMatched) {
        if (matchCount == SECOND_PRIZE.matchCount) {
            if (isBonusNumberMatched) {
                return Optional.of(SECOND_PRIZE);
            }
            return Optional.of(THIRD_PRIZE);
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst();
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
