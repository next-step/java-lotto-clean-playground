package domain.result;

import domain.money.PrizeAmount;
import domain.number.MatchCount;
import java.util.List;
import java.util.Optional;

public enum LottoRank {
    THREE_MATCHES(3, 5_000),
    FOUR_MATCHES(4, 50_000),
    FIVE_MATCHES(5, 1_500_000),
    SIX_MATCHES(6, 2_000_000_000);

    private final int matchCount;
    private final PrizeAmount prizeAmount;

    LottoRank(int matchCount, long prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = PrizeAmount.from(prizeAmount);
    }

    public static Optional<LottoRank> findBy(MatchCount matchCount) {
        return valuesForResult().stream()
                .filter(rank -> rank.matches(matchCount))
                .findFirst();
    }

    public static List<LottoRank> valuesForResult() {
        return List.of(THREE_MATCHES, FOUR_MATCHES, FIVE_MATCHES, SIX_MATCHES);
    }

    private boolean matches(MatchCount other) {
        return other.isSame(matchCount);
    }

    public PrizeAmount totalPrize(int count) {
        return prizeAmount.multiply(count);
    }

    public String resultMessage(int count) {
        return matchCount + "개 일치 (" + prizeText() + "원)- " + count + "개";
    }

    private String prizeText() {
        return String.valueOf(prizeAmountValue());
    }

    private long prizeAmountValue() {
        return prizeAmount.valueForDisplay();
    }
}
