package lotto;

import java.util.Arrays;
import java.util.List;

public class MatchResult {
    public static final MatchResult NONE = new MatchResult(LottoBonus.LOSING_PLACE);

    private static boolean bonusMatch;
    private final LottoBonus bonus;

    public MatchResult(LottoBonus bonus) {
        this.bonus = bonus;
    }
    
    public static MatchResult of(int matchCount) {
        LottoBonus lottoBonus = LottoBonus.valueOf(matchCount, bonusMatch);
        return new MatchResult(lottoBonus);
    }

    public static List<MatchResult> getAll() {
        return Arrays.stream(LottoBonus.values())
                .filter(b -> b != LottoBonus.LOSING_PLACE)
                .map(MatchResult::new)
                .toList();
    }

    public int getMatchCount() {
        return bonus.getMatchCount();
    }

    public Money getPrizeAmount() {
        return bonus.getWinningMoney();
    }

    public boolean isBonusMatch() {
        return bonus.isNeedBonusMatch();
    }

    public boolean isWinning() {
        return bonus != LottoBonus.LOSING_PLACE;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof MatchResult)) return false;
        MatchResult matchResult = (MatchResult) object;
        return bonus == matchResult.bonus;
    }

    @Override
    public int hashCode() {
        return bonus.hashCode();
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
