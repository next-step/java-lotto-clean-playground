package model;

import java.util.*;

public class LottoPrizeResult {
    private final Map<LottoPrize, Integer> prizeResult;
    int lottoCount;

    public LottoPrizeResult() {
        this.prizeResult = new EnumMap<LottoPrize, Integer>(LottoPrize.class);
        this.lottoCount = 0;

        for (LottoPrize lottoPrize : LottoPrize.values()) {
            prizeResult.put(lottoPrize, 0);
        }
    }

    public Map<LottoPrize, Integer> getValue() {
        return Collections.unmodifiableMap(prizeResult);
    }

    public void addPrize(int matchNumberCount, boolean hasBonusNumber) {
        LottoPrize currentLottoPrize = LottoPrize.getPrizeByMatchResult(matchNumberCount, hasBonusNumber);

        if (currentLottoPrize != null) {
            prizeResult.put(currentLottoPrize, prizeResult.get(currentLottoPrize) + 1);
        }

        lottoCount += 1;
    }

    public double getProfitRate() {
        int purchasePrice = lottoCount * LottoConstants.PRICE.getValue();

        if (purchasePrice == 0) {
            return 0;
        }

        int totalPrizeMoney =
                prizeResult.entrySet().stream()
                        .mapToInt(entry -> entry.getKey().getMoney() * entry.getValue())
                        .sum();

        return (double) totalPrizeMoney / purchasePrice;
    }
}
