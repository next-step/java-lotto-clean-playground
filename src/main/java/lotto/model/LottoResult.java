package lotto.model;

import static lotto.global.Constants.LOTTO_COST;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import lotto.global.Rank;

public class LottoResult {
    private final Map<Rank, Integer> result;
    private final int money;

    private LottoResult(final Map<Rank, Integer> result, final int money) {
        this.result = result;
        this.money = money;
    }

    public static LottoResult of(final Lottos lottos, final WinNumbers winNumbers) {
        final Map<Rank, Integer> result = initMap();
        for (Lotto lotto : lottos.getLottos()) {
            final Rank rank = lotto.getRank(winNumbers);
            result.put(rank, result.get(rank) + 1);
        }

        return new LottoResult(result, lottos.size() * LOTTO_COST);
    }

    private static Map<Rank, Integer> initMap() {
        final Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        return result;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public double getRateOfReturn() {
        return getReward() / (double) money;
    }

    private int getReward() {
        return result.keySet().stream()
            .mapToInt(rank -> rank.getReward() * result.get(rank))
            .sum();
    }
}
