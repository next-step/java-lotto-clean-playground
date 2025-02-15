package domain;

import java.util.stream.Stream;

public enum LottoRank {

    FIRST_PLACE(6, 2_000_000_000L, BonusMask.ANY),
    SECOND_PLACE(5, 30_000_000L, BonusMask.HAS_BONUS),
    THIRD_PLACE(5, 1_500_000L,      BonusMask.NO_BONUS),
    FOURTH_PLACE(4, 50_000L, BonusMask.ANY),
    FIFTH_PLACE(3, 5_000L, BonusMask.ANY),
    NO_PLACE(0, 0L, BonusMask.ANY);

    private final Integer matchedCount;
    private final Long prize;
    private final Integer bonusMask;

    LottoRank(int matchedCount, long prize, int bonusMask) {
        this.matchedCount = matchedCount;
        this.prize = prize;
        this.bonusMask = bonusMask;
    }

    public static LottoRank of(int matchedCount, boolean isBonusNumber) {
        return Stream.of(values())
                .filter(rank -> rank.matchedCount == matchedCount &&
                        isBonusMatch(isBonusNumber, rank.bonusMask))
                .findAny()
                .orElse(NO_PLACE);
    }

    private static boolean isBonusMatch(boolean isBonusNumber, int bonusMask) {
        return (toBonusMask(isBonusNumber) & bonusMask) != 0;
    }

    private static int toBonusMask(boolean isBonusNumber) {
        if (isBonusNumber) {
            return BonusMask.HAS_BONUS;
        }
        return BonusMask.NO_BONUS;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public long getPrize() {
        return prize;
    }

    public boolean isBonusNumber() {
        return bonusMask == BonusMask.HAS_BONUS;
    }

    private static class BonusMask {
        public static final int NO_BONUS = 1;
        public static final int HAS_BONUS = 2;
        public static final int ANY = 3;
    }
}
