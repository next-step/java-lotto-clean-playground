package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public Map<Rank,Integer> getRankCount(Lotto winnerLotto) {

        Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = Rank.from(lotto.getCount(winnerLotto));
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
        return rankCount;
    }

}
