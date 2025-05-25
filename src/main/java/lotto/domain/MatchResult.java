package lotto.domain;

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
    }

}
