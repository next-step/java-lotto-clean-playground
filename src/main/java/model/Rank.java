package model;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rank {
    FIRST((matchCount, matchBonusBall) -> matchCount == 6, 2_000_000_000),
    SECOND((matchCount, matchBonusBall) -> matchCount == 5 && matchBonusBall,30_000_000),
    THIRD((matchCount, matchBonusBall) -> matchCount == 5 && !matchBonusBall, 1_500_000),
    FOURTH((matchCount, matchBonusBall) -> matchCount == 4, 50_000),
    FIFTH((matchCount, matchBonusBall) -> matchCount == 3, 5_000),
    NONE((matchCount,matchBonusBall)->false, 0);

    private final BiPredicate<Integer,Boolean> matchCondition;
    private final int prize;

    Rank(BiPredicate<Integer, Boolean> matchCondition, int prize) {
        this.matchCondition = matchCondition;
        this.prize = prize;
    }
    public int getPrize() {
        return prize;
    }

    public boolean isMatch(int count, boolean bonus) {
        return matchCondition.test(count, bonus);
    }

    public static Rank of(int count, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.isMatch(count, bonus))
                .findFirst()
                .orElse(NONE);
    }

}
