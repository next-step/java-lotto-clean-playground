package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos, int expectedCount) {
        validateSize(lottos, expectedCount);
        this.lottos = lottos;
    }

    public static Lottos merge(Lottos manual, Lottos random) {
        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manual.getLottos());
        allLottos.addAll(random.getLottos());
        return new Lottos(allLottos, allLottos.size());
    }

    private void validateSize(List<Lotto> lottos, int expectedCount) {
        if (lottos.size() != expectedCount) {
            throw new IllegalArgumentException("구매 수량이 일치하지 않습니다.");
        }
    }

    public int size() {
        return lottos.size();
    }

    public void calculateResults(Lotto winnerNumbers, LottoNumber bonusNumber, LottoCalculator calculator) {
        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchCount(winnerNumbers);
            boolean bonus = lotto.contains(bonusNumber);
            Rank rank = Rank.MISS.find(matchCount, bonus);
            calculator.valueAdd(rank);
        }
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
