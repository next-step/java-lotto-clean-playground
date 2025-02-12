package domain;

import java.util.stream.Stream;

public enum LottoRank {

    FIRST_PLACE(6, 2_000_000_000L),
    SECOND_PLACE(5, 1_500_000L),
    THIRD_PLACE(4, 50_000L),
    FOURTH_PLACE(3, 5_000L),
    NO_PLACE(0, 0L);

    private final int matchedCount;
    private final long prize;

    LottoRank(int matchedCount, long prize) {
        this.matchedCount = matchedCount;
        this.prize = prize;
    }

    public static LottoRank of(int matchedCount) {
        return Stream.of(values())
                .filter(rank -> rank.matchedCount == matchedCount)
                .findAny()
                .orElse(NO_PLACE);
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public long getPrize() {
        return prize;
    }
}
