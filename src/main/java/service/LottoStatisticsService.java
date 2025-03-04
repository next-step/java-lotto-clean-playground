package service;

import domain.*;
import java.util.*;
import java.util.stream.*;

public class LottoStatisticsService {

    public LottoStatistics calculateStatistics(Lottos lottos, WinningLottoNumbers winningNumbers) {
        Map<WinningRank, Integer> statistics = lottos.getLottos().stream()
                .map(lotto -> determineRank(lotto, winningNumbers))
                .flatMap(Optional::stream)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.summingInt(count -> 1)));

        return new LottoStatistics(statistics);
    }

    private Optional<WinningRank> determineRank(Lotto lotto, WinningLottoNumbers winningNumbers) {
        int matchCount = Lotto.countMatchingNumbers(lotto, winningNumbers.getWinningLotto());
        boolean bonusMatch = winningNumbers.isBonusMatched(lotto);
        return WinningRank.valueOf(matchCount, bonusMatch);
    }
}
