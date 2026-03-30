package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<List<Integer>> toNumberLists() {
        return lottos.stream()
                .map(this::toNumbers)
                .toList();
    }

    public WinningStatistics createWinningStatistics(Lotto winningLotto) {
        WinningStatistics winningStatistics = new WinningStatistics();
        for (Lotto lotto : lottos) {
            winningStatistics.add(findRank(lotto, winningLotto));
        }
        return winningStatistics;
    }

    private List<Integer> toNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::number)
                .toList();
    }


    private Rank findRank(Lotto lotto, Lotto winningLotto) {
        return Rank.from(lotto.countMatch(winningLotto));
    }
}
