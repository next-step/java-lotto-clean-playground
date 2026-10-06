package domain.lotto;

import domain.result.LottoResult;
import domain.result.MatchResult;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public Lottos combine(Lottos other) {
        List<Lotto> combined =  new ArrayList<>(lottos);
        combined.addAll(other.lottos);

        return new Lottos(combined);
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
