package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<List<Integer>> getStatus() {
        List<List<Integer>> status = new ArrayList<>();
        for (Lotto lotto : lottos) {
            status.add(lotto.numbers());
        }
        return status;
    }

    public List<Score> getScores(List<Integer> winningNumbers) {
        List<Score> scores = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchingNumberCount = lotto.getMatchingNumberCount(winningNumbers);
            scores.add(new Score(matchingNumberCount));
        }
        return scores;
    }
}
