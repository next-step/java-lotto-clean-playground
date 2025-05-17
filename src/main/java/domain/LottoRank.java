package domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    THREE_MATCH(3, 5_000, "3개 일치 (5000원)"),
    FOUR_MATCH(4, 50_000, "4개 일치 (50000원)"),
    FIVE_MATCH(5, 1_500_000, "5개 일치 (1500000원)"),
    SIX_MATCH(6, 2_000_000_000, "6개 일치 (2000000000원)");

    private final int matchCount;
    private final int prize;
    private final String message;

    LottoRank(int matchCount, int prize, String message) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.message = message;
    }

    public static Optional<LottoRank> of(int matchCount) {
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
