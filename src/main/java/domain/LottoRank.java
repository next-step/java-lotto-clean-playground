package domain;

import java.util.stream.Stream;

public enum LottoRank {

    FIRST_PLACE(6, 2_000_000_000L, 3),
    SECOND_PLACE(5, 30_000_000L, 2),
    THIRD_PLACE(5, 1_500_000L, 1),
    FOURTH_PLACE(4, 50_000L, 3),
    FIFTH_PLACE(3, 5_000L, 3),
    NO_PLACE(0, 0L, 3);

    private final int matchedCount;
    private final long prize;
    private final int bonusMask;

    LottoRank(int matchedCount, long prize, int bonusMask) {
        this.matchedCount = matchedCount;
        this.prize = prize;
        this.bonusMask = bonusMask;
    }

    public static LottoRank of(int matchedCount, boolean isBonusNumber) {
        return Stream.of(values())
                .filter(rank -> rank.matchedCount == matchedCount &&
                        ((toBonusMask(isBonusNumber) & rank.bonusMask) != 0) )
                .findAny()
                .orElse(NO_PLACE);
    }

    private static int toBonusMask(boolean isBonusNumber) {
        if(isBonusNumber) {
            return 2;
        }
        return 1;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public long getPrize() {
        return prize;
    }
}
