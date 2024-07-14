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

    public List<Score> getScores(WinningLotto winningLotto) {
        List<Score> scores = new ArrayList<>();
        for (Lotto lotto : lottos) {
            final Score score = getScore(lotto, winningLotto);
            scores.add(score);
        }
        return scores;
    }

    private Score getScore(Lotto lotto, WinningLotto winningLotto) {
        final List<Integer> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        final int bonusBallNumber = winningLotto.getBonusBallNumber();

        int matchingNumberCount = lotto.getMatchingNumberCount(winningLottoNumbers);
        final boolean isMatchingBonusNumber = lotto.isContains(bonusBallNumber);

        return new Score(matchingNumberCount, isMatchingBonusNumber);
    }
}
