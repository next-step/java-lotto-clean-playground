package lotto;

import java.util.Arrays;
import java.util.List;

public class MatchResult {
    private final int matchCount;
    private final Money prizeAmount;
    private static final MatchResult THREE = new MatchResult(3, new Money(5000));
    private static final MatchResult FOUR  = new MatchResult(4, new Money(50000));
    private static final MatchResult FIVE  = new MatchResult(5, new Money(1500000));
    private static final MatchResult SIX   = new MatchResult(6, new Money(2000000000));
    public static final MatchResult NONE  = new MatchResult(0, new Money(0));
    private static final List<MatchResult> VALUES = Arrays.asList(THREE, FOUR, FIVE, SIX, NONE);

    private MatchResult(int matchCount, Money prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }


    public static MatchResult of(int count) {
        for (MatchResult matchResult : VALUES) {
            if (matchResult.matchCount == count) return matchResult;
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Money getPrizeAmount() {
        return prizeAmount;
    }

    public static List<MatchResult> getAll() {
        return VALUES;
    }

}
