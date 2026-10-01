package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Integer> getMatchCounts(
            Lotto winningLotto
    ) {
        List<Integer> matchCounts = new ArrayList<>(
                Arrays.asList(0, 0, 0, 0, 0, 0, 0)
        );

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatches(winningLotto);

            int currentCount = matchCounts.get(matchCount);

            matchCounts.set(matchCount, currentCount + 1);
        }

        return matchCounts;
    }
}
