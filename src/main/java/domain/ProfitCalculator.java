package domain;

import enums.LottoRank;

import java.math.BigDecimal;
import java.util.Map;

import static domain.LottoShop.PRICE_PER_TICKET;

public abstract class ProfitCalculator {

    public static BigDecimal calculateProfitRate(Map<LottoRank, Integer> resultByRank, LottoCount lottoCount) {
        if (resultByRank == null) {
            throw new IllegalStateException("로또 결과가 생성되지 않았습니다.");
        }
        PrizeMoney totalPrize = calculateTotalPrize(resultByRank);
        BigDecimal lottoCountDecimal  = BigDecimal.valueOf(lottoCount.getLottoCount());

        return totalPrize.getAmount().divide(lottoCountDecimal.multiply(PRICE_PER_TICKET));
    }

    public static PrizeMoney calculateTotalPrize(Map<LottoRank, Integer> resultByRank) {
        PrizeMoney totalPrize = new PrizeMoney(BigDecimal.valueOf(0));

        for (Map.Entry<LottoRank, Integer> matchCount : resultByRank.entrySet()) {
            PrizeMoney prizePerRank = new PrizeMoney(matchCount.getKey().getPrize());
            PrizeMoney prizeTotal = prizePerRank.multiply(matchCount.getValue());
            totalPrize = totalPrize.plus(prizeTotal);
        }
        System.out.println(totalPrize.getAmount());
        return totalPrize;
    }
}
