package domain.purchase;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Integer> countMatches(Lotto winningLotto) {
        List<Integer> matches = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int count = lotto.countMatches(winningLotto);
            matches.add(count);
        }
        return matches;
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
