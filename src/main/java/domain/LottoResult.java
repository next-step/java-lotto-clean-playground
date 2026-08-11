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
}
