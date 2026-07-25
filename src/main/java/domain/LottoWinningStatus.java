package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoWinningStatus {
    private final Lottos lottos;
    private final WinningNumbers winningNumbers;
    private final PrizeMoneyTable prizeMoneyTable;

    public LottoWinningStatus(Lottos lottos, WinningNumbers winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
        this.prizeMoneyTable = new PrizeMoneyTable();
    }

    public MatchingNumberCounts matchingNumberCounts() {
        return lottos.countMatches(winningNumbers);
    }

    public Double getPrizeRate(Integer purchaseAmount) {
        int totalPrizeMoney = 0;
        for (int count : matchingNumberCounts().getMatchingNumberCounts()) {
            totalPrizeMoney += prizeMoneyTable.getPrize(count);
        }
        return (double) totalPrizeMoney / purchaseAmount;
    }

    public LottoCountByMatchNumber getLottoCountByMatchNumber() {
        Map<Integer, Integer> countByMatchNumber = new HashMap<>();
        List<Integer> counts = matchingNumberCounts().getMatchingNumberCounts();
        for (int matchNumber : prizeMoneyTable.matchNumbers()) {
            countByMatchNumber.put(matchNumber, Collections.frequency(counts, matchNumber));
        }
        return new LottoCountByMatchNumber(countByMatchNumber);
    }

    public PrizeMoneyTable getPrizeMoneyTable() {
        return prizeMoneyTable;
    }
}
