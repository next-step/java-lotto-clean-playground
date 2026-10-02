package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public LottoResult calculateResult(WinningLotto winningLotto) {
        List<MatchResult> matchResults = new ArrayList<>();

        for (Lotto lotto : lottos) {
            matchResults.add(winningLotto.createMatchResult(lotto));
        }

        return new LottoResult(matchResults);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
