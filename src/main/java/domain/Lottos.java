package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoCalculator matchAll(WinningLotto winningLotto) {
        LottoCalculator calculator = new LottoCalculator();
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            calculator.valueAdd(rank);
        }
        return calculator;
    }

    public static Lottos of(List<Lotto> manuals, List<Lotto> autos) {
        List<Lotto> combined = new ArrayList<>();
        combined.addAll(manuals);
        combined.addAll(autos);
        return new Lottos(combined);
    }

}