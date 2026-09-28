package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Integer> getMatchCount(Lotto winnerLotto) {

        List<Integer> matchCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));

        for (Lotto lotto : lottos) {
            List<Integer> copy = new ArrayList<>(lotto.getNumbers());
            copy.retainAll(winnerLotto.getNumbers());

            int count = copy.size();
            matchCount.set(count, matchCount.get(count) + 1);
        }

        return matchCount;
    }

}
