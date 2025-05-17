package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> lottoResultMap = new EnumMap<>(LottoRank.class);
    private final Lottos lottos;
    private final WinningLotto winningLotto;
    private final int purchasePrice;

    public LottoResult(Lottos lottos, WinningLotto winningLotto, int purchasePrice) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
        this.purchasePrice = purchasePrice;
        for (LottoRank rank : LottoRank.values()) {
            lottoResultMap.put(rank, 0);
        }
    }

    public void calculateRank() {
        for (Lotto lotto : lottos.lottos()) {
            int matchCount = lotto.getMatchCount(lotto, winningLotto.getLotto());
            boolean isBonusNumberMatched = winningLotto.isMatchBonusNumber(lotto);
            LottoRank.of(matchCount, isBonusNumberMatched).ifPresent(rank ->
                    lottoResultMap.put(rank, lottoResultMap.get(rank) + 1)
            );
        }
    }

    private double calculateROI() {
        double totalReturn = lottoResultMap.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();

        return totalReturn / purchasePrice;
    }

    public Map<LottoRank, Integer> getLottoResultMap() {
        return lottoResultMap;
    }

    public double getROI() {
        return calculateROI();
    }

}
