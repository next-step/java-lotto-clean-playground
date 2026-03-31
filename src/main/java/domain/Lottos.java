package domain;

import dto.LottoStatus;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottoList) {
        this.lottos = List.copyOf(lottoList);
    }

    public Map<LottoRank, Integer> calculateMatchCounts(List<LottoNumber> winningNumbers) {
        Map<LottoRank, Integer> matchingCounts = new LinkedHashMap<>();

        Arrays.stream(LottoRank.values())
                .forEach(rank -> matchingCounts.put(rank, 0));

        for (Lotto lotto : lottos) {
            int count = lotto.countMatchingNumbers(winningNumbers);
            updateCount(matchingCounts, count);
        }

        return Map.copyOf(matchingCounts);
    }

    public int quantity() {
        return lottos.size();
    }

    public List<LottoStatus> toStatus() {
        return lottos.stream()
                .map(Lotto::numbers)
                .toList();
    }

    private void updateCount(Map<LottoRank, Integer> matchingCounts, int count) {
        LottoRank rank = LottoRank.getLottoRank(count);
        matchingCounts.put(rank, matchingCounts.get(rank) + 1);
    }
}
