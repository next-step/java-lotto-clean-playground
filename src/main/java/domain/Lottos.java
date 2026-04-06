package domain;

import dto.LottoStatistics;
import dto.LottoStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottoList) {
        this.lottos = List.copyOf(lottoList);
    }

    public LottoStatistics getLottoStatistics(Lotto winningLotto, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> matchingCounts = calculateMatchedCounts(winningLotto, bonusNumber);
        BigDecimal profitRate = calculateProfitRate(matchingCounts);

        return new LottoStatistics(matchingCounts, profitRate);
    }

    public List<LottoStatus> toStatus() {
        return lottos.stream()
                .map(Lotto::getLottoStatus)
                .toList();
    }

    private Map<LottoRank, Integer> calculateMatchedCounts(Lotto winningLotto, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> matchedCounts = new LinkedHashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(rank -> matchedCounts.put(rank, 0));
        lottos.forEach(lotto -> {
            int count = lotto.countMatchingNumbers(winningLotto);
            boolean hasBonus = lotto.contains(bonusNumber);
            updateCount(matchedCounts, count, hasBonus);
        });

        return matchedCounts;
    }

    private BigDecimal calculateProfitRate(Map<LottoRank, Integer> matchedCounts) {
        long totalPrize = matchedCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        BigDecimal totalProfit = BigDecimal.valueOf(totalPrize);
        BigDecimal purchasedPrice = BigDecimal.valueOf(lottos.size() * 1000L);

        return totalProfit.divide(purchasedPrice, 2, RoundingMode.HALF_UP);
    }

    private void updateCount(Map<LottoRank, Integer> matchedCounts, int count, boolean hasBonus) {
        LottoRank rank = LottoRank.getLottoRank(count, hasBonus);
        matchedCounts.put(rank, matchedCounts.get(rank) + 1);
    }
}
