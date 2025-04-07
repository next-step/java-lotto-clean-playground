package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Rank, Integer> countResult(WinningLotto winningLotto) {
        Map<Rank, Integer> result = initializeRankMap();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            updateResult(result, rank);
        }

        return result;
    }

    private Map<Rank, Integer> initializeRankMap() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    private void updateResult(Map<Rank, Integer> result, Rank rank) {
        if (rank == Rank.NONE) {
            return;
        }
        result.put(rank, result.get(rank) + 1);
    }

    public long calculateTotalPrize(WinningLotto winningLotto) {
        return countResult(winningLotto).entrySet().stream()
                .mapToLong(e -> e.getKey().getPrize() * e.getValue())
                .sum();
    }

    public int countManual() {
        return (int) lottos.stream().filter(lotto -> lotto.getType() == LottoType.MANUAL).count();
    }

    public int countAuto() {
        return (int) lottos.stream().filter(lotto -> lotto.getType() == LottoType.AUTO).count();
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

}
