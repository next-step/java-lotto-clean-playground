package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottoList) {
        this.lottos = List.copyOf(lottoList);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public Map<LottoRank, Integer> calculateMatchedCounts(Lotto winningLotto, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> matchedCounts = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(rank -> matchedCounts.put(rank, 0));
        lottos.forEach(lotto -> {
            int count = lotto.countMatchingNumbers(winningLotto);
            boolean hasBonus = lotto.contains(bonusNumber);
            updateCount(matchedCounts, count, hasBonus);
        });

        return Map.copyOf(matchedCounts);
    }

    public BigDecimal calculateProfitRate(Map<LottoRank, Integer> matchedCounts) {
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
