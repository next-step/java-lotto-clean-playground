package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos merge(Lottos manual, Lottos random) {
        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manual.getLottos());
        allLottos.addAll(random.getLottos());
        return new Lottos(allLottos);
    }

    public int size() {
        return lottos.size();
    }

    public void calculateResults(Lotto winnerNumbers, LottoNumber bonusNumber, LottoCalculator calculator) {
        for (Lotto lotto : lottos) {
            Rank rank = lotto.calculateRank(winnerNumbers, bonusNumber);
            calculator.valueAdd(rank);
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
