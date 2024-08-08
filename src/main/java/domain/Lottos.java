package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public int getSize() {
        return lottos.size();
    }

    public List<List<Integer>> getStatus() {
        List<List<Integer>> status = new ArrayList<>();
        for (Lotto lotto : lottos) {
            status.add(lotto.getNumbers());
        }
        return status;
    }

    public List<Score> getScores(Lotto winningLotto, BonusBall bonusBall) {
        List<Score> scores = new ArrayList<>();
        for (Lotto lotto : lottos) {
            final Score score = getScore(lotto, winningLotto, bonusBall);
            scores.add(score);
        }
        return scores;
    }

    private Score getScore(Lotto lotto, Lotto winningLotto, BonusBall bonusBall) {
        int matchingNumberCount = lotto.getMatchingNumberCount(winningLotto);
        final boolean isMatchingBonusNumber = lotto.isContains(bonusBall);

        return new Score(matchingNumberCount, isMatchingBonusNumber);
    }
}
