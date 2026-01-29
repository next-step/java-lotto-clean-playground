package lotto.domain.model;

public class Judge {

    private Judge() {

    }

    public static void calculateResults(Lottos lottos, WinningLotto winningLotto, WinningStatistics statistics) {
        for (Lotto lotto : lottos.getValues()) {
            int matchCount = lotto.countMatch(winningLotto.getWinningLotto());

            boolean matchBonus = lotto.getNumbers().contains(winningLotto.getBonusNumber());

            LottoRank rank = LottoRank.valueOf(matchCount, matchBonus);
            statistics.addResult(rank);
        }
    }

}
