package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    private final Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);

    public LottoStatistics(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        calculate(lottos.getLottos(), winningNumbers, bonusNumber);
    }

    public int countOf(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    private void calculate(List<Lotto> lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        for (Lotto lotto : lottos) {
            Rank rank = matchRank(winningNumbers, bonusNumber, lotto);
            saveRank(rank);
        }
    }

    private Rank matchRank(WinningNumbers winningNumbers, BonusNumber bonusNumber, Lotto lotto) {
        int matchCount = winningNumbers.countMatch(lotto.getNumbers());

        boolean bonusMatch = lotto.getNumbers().containsValue(bonusNumber.value());

        return Rank.valueOf(matchCount, bonusMatch);
    }

    private void saveRank(Rank rank) {
        if (!rank.isWinning()) {
            return;
        }
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }
}
