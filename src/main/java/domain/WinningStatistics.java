package domain;

import java.util.*;

public class WinningStatistics {
    Map<Rank, WinnerNum> winningStatistics = new HashMap<>();

    MatchCount matchCount = new MatchCount();
    public WinningStatistics() {
        for (Rank rank : Rank.values()) {
            winningStatistics.put(rank, new WinnerNum(0));
        }
    }

    public void compareLottos (List<Integer> winningNumbers, Lottos lottos, int bonusNumber) {
        for (LottoNumber lottoNumber : lottos.getLottos()) {
            matchCount.comparingLotto(lottoNumber.getLottoNumbers(), winningNumbers);
            Rank rank = Rank.getRank(matchCount.getCount(), matchCount.hasBonusNumber(lottoNumber.getLottoNumbers(), bonusNumber));
            winningStatistics.get(rank).increase();
        }
    }

    public Map<Rank, WinnerNum> getWinningStatistics() {
        return winningStatistics;
    }
}
