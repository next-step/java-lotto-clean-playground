package model;

import global.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult getResult(final Lotto winningLotto, final LottoNumber bonusNumber) {
        final Map<Rank, Integer> result = initMap();
        for (Lotto lotto : lottos) {
            final Rank rank = lotto.getRank(winningLotto, bonusNumber);
            result.put(rank, result.get(rank) + 1);
        }

        return new LottoResult(result);
    }

    private Map<Rank, Integer> initMap() {
        final Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        return result;
    }

    public int getBuyLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
