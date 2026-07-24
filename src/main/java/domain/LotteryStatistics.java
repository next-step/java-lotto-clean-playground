package domain;

import java.util.List;

public class LotteryStatistics {
    private int threeMatchCount = 0;
    private int fourMatchCount = 0;
    private int fiveMatchCount = 0;
    private int sixMatchCount = 0;

    public void checkLottos(List<Lotto> lottos, Lotto winningLotto) {
        int match = 0;
        for(Lotto lotto : lottos) {
            lotto.matchCount(winningLotto);
        }
    }

}
