package domain.lotto;

import domain.result.LottoStatistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchasedLottos {
    private final List<LottoTicket> values;

    public PurchasedLottos(List<LottoTicket> values) {
        this.values = List.copyOf(values);
    }

    public static PurchasedLottos empty() {
        return new PurchasedLottos(List.of());
    }

    public int size() {
        return values.size();
    }

    public List<LottoTicket> values() {
        return Collections.unmodifiableList(values);
    }

    public PurchasedLottos addAll(List<LottoTicket> lottos) {
        List<LottoTicket> combinedLottos = new ArrayList<>(values);
        combinedLottos.addAll(lottos);
        return new PurchasedLottos(combinedLottos);
    }

    public LottoStatistics calculateLottoStatistics(WinningLotto winningLotto) {
        LottoStatistics lottoStatistics = LottoStatistics.empty();
        values.forEach(lottoTicket -> lottoStatistics.record(winningLotto.match(lottoTicket)));
        return lottoStatistics;
    }
}
