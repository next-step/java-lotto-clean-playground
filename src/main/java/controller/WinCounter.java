package controller;

import java.util.EnumMap;
import java.util.List;
import model.Lotto;
import model.Rank;

public class WinCounter {

    private EnumMap<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public EnumMap<Rank, Integer> countWins(List<Lotto> lottos, WinningLotto winningLotto) {
        initCounts();
        countCounts(lottos, winningLotto);
        return counts;
    }

    public void initCounts() {
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
    }

    public void countCounts(List<Lotto> lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.findRank(lotto);
            if (rank != null) {
                counts.put(rank, counts.get(rank) + 1);
            }
        }
    }


}
