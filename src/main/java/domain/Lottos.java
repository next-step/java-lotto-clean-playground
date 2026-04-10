package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottoList) {
        this.lottos = List.copyOf(lottoList);
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public LottoWinningResult generateWinningResult(Lotto winningLotto, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> matchedCounts = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values())
                .forEach(rank -> matchedCounts.put(rank, 0));
        lottos.forEach(lotto -> {
            int count = lotto.countMatchingNumbers(winningLotto);
            boolean hasBonus = lotto.contains(bonusNumber);
            updateCount(matchedCounts, count, hasBonus);
        });

        return new LottoWinningResult(matchedCounts, lottos.size());
    }

    private void updateCount(Map<LottoRank, Integer> matchedCounts, int count, boolean hasBonus) {
        LottoRank rank = LottoRank.getLottoRank(count, hasBonus);
        matchedCounts.put(rank, matchedCounts.get(rank) + 1);
    }
}
