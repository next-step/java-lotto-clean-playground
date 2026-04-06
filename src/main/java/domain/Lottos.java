package domain;

import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoCalculator matchAll(Lotto winnerNumbers) {
        LottoCalculator calculator = new LottoCalculator();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchNumbers(winnerNumbers);
            Rank rank = Rank.valueOf(matchCount);
            calculator.valueAdd(rank);
        }
        return calculator;
    }

}