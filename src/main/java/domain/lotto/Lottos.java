package domain.lotto;

import domain.result.WinningStatistics;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> values;

    public Lottos(List<Lotto> values) {
        this.values = List.copyOf(values);
    }

    public int size() {
        return values.size();
    }

    public List<Lotto> values() {
        return Collections.unmodifiableList(values);
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        WinningStatistics winningStatistics = WinningStatistics.empty();
        values.forEach(lotto -> winningStatistics.record(lotto.countMatching(winningLotto)));
        return winningStatistics;
    }
}
