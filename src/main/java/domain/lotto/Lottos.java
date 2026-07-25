package domain.lotto;

import domain.result.LottoStatistics;
import java.util.ArrayList;
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

    public Lottos addAll(List<Lotto> lottos) {
        List<Lotto> combinedLottos = new ArrayList<>(values);
        combinedLottos.addAll(lottos);
        return new Lottos(combinedLottos);
    }

    public LottoStatistics calculateLottoStatistics(WinningLotto winningLotto) {
        LottoStatistics lottoStatistics = LottoStatistics.empty();
        values.forEach(lotto -> lottoStatistics.record(winningLotto.match(lotto)));
        return lottoStatistics;
    }
}
