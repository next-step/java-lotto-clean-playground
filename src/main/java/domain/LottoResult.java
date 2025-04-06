package domain;

import enums.LottoRank;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> resultByRank;
    private final LottoWinningNumbers winningNumbers;
    private final LottoPurchase lottoPurchase;
    private final Double profitRate;

    private LottoResult(LottoWinningNumbers winningNumbers, LottoPurchase lottoPurchase) {
        this.winningNumbers = winningNumbers;
        this.lottoPurchase = lottoPurchase;
        resultByRank = calculateMatchCount();
        profitRate = calculateProfitRate();
    }

    public static LottoResult createLottoResult(LottoWinningNumbers winningNumbers, LottoPurchase lottoPurchase) {
        return new LottoResult(winningNumbers, lottoPurchase);
    }

    public Map<LottoRank, Integer> getResultByRank() {
        return Collections.unmodifiableMap(resultByRank);
    }

    public Double getProfitRate() {
        return profitRate;
    }

    Double calculateProfitRate() {
        PrizeMoney totalPrize = calculatePrize();
        return totalPrize.getAmount() / (lottoPurchase.getMoney().getAmount());
    }

    Map<LottoRank, Integer> calculateMatchCount() {
        Map<LottoRank, Integer> resultByRank = initResultByRank();

        List<Lotto> lottos = lottoPurchase.getLottos();
        for (Lotto lotto : lottos) {
            LottoRank lottoRank = LottoRank.from(winningNumbers.matchCount(lotto), winningNumbers.bonusMatch(lotto));
            resultByRank.put(lottoRank, resultByRank.get(lottoRank) + 1);
        }
        return resultByRank;
    }

    PrizeMoney calculatePrize() {
        PrizeMoney totalPrize = new PrizeMoney(0.0);

        for (Map.Entry<LottoRank, Integer> matchCount : resultByRank.entrySet()) {
            PrizeMoney prizePerRank = new PrizeMoney(matchCount.getKey().getPrize());
            PrizeMoney prizeTotal = prizePerRank.multiply(matchCount.getValue());
            totalPrize = totalPrize.plus(prizeTotal);
        }
        return totalPrize;
    }

    private Map<LottoRank, Integer> initResultByRank() {
        Map<LottoRank, Integer> resultByRank = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            resultByRank.put(lottoRank, 0);
        }

        return resultByRank;
    }
}
