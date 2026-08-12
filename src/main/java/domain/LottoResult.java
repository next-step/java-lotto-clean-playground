package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {

    public List<Rank> calculateRanks(List<Lotto> lottos, WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : lottos) {
            ranks.add(winningLotto.findRank(lotto));
        }
        return ranks;
    }

    private static int countRank(List<Rank> ranks, Rank target) {
        int count = 0;

        for (Rank rank : ranks) {
            if (rank == target) {
                count++;
            }
        }

        return count;
    }

    public List<Integer> countRanks(List<Rank> ranks) {
        List<Integer> counts = new ArrayList<>();

        counts.add(countRank(ranks, Rank.FIFTH));
        counts.add(countRank(ranks, Rank.FOURTH));
        counts.add(countRank(ranks, Rank.THIRD));
        counts.add(countRank(ranks, Rank.SECOND));
        counts.add(countRank(ranks, Rank.FIRST));

        return counts;
    }
}
