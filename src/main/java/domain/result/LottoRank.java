package domain.result;

import domain.money.PrizeAmount;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    THREE_MATCHES(3, BonusBallMatchCondition.ANY, 5_000),
    FOUR_MATCHES(4, BonusBallMatchCondition.ANY, 50_000),
    FIVE_MATCHES(5, BonusBallMatchCondition.UNMATCHED, 1_500_000),
    BONUS_BALL_MATCHES(5, BonusBallMatchCondition.MATCHED, 30_000_000),
    SIX_MATCHES(6, BonusBallMatchCondition.ANY, 2_000_000_000);

    private final int matchCount;
    private final BonusBallMatchCondition bonusBallMatchCondition;
    private final PrizeAmount prizeAmount;

    LottoRank(
            int matchCount,
            BonusBallMatchCondition bonusBallMatchCondition,
            long prizeAmount
    ) {
        this.matchCount = matchCount;
        this.bonusBallMatchCondition = bonusBallMatchCondition;
        this.prizeAmount = PrizeAmount.from(prizeAmount);
    }

    public static Optional<LottoRank> findBy(LottoResult lottoResult) {
        return valuesForResult().stream()
                .filter(rank -> rank.matches(lottoResult))
                .findFirst();
    }

    public static List<LottoRank> valuesForResult() {
        return List.of(THREE_MATCHES, FOUR_MATCHES, FIVE_MATCHES, BONUS_BALL_MATCHES, SIX_MATCHES);
    }

    private boolean matches(LottoResult lottoResult) {
        return lottoResult.hasMatchCount(matchCount)
                && bonusBallMatchCondition.matches(lottoResult);
    }

    public PrizeAmount totalPrize(int count) {
        return prizeAmount.multiply(count);
    }

    public int matchCount() {
        return matchCount;
    }

    public long prizeAmount() {
        return prizeAmount.value();
    }

    public boolean requiresBonusBallMatch() {
        return bonusBallMatchCondition == BonusBallMatchCondition.MATCHED;
    }
}
