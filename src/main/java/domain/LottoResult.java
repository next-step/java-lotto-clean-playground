package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> matchResults;

    public LottoResult(LottoTickets lottoTickets, Lotto winningLotto) {
        this.matchResults = new EnumMap<>(Rank.class);
        initResults();
        calculate(lottoTickets.getLottoNumber(), winningLotto.getNumbers());
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            matchResults.put(rank, 0);
        }
    }

    private void calculate(List<Lotto> lottoNumber, List<Integer> winningNumbers) {
        for (Lotto lotto : lottoNumber) {
            int matchCount = countMatch(lotto.getNumbers(), winningNumbers);
            Rank rank = Rank.valueOfMatchCount(matchCount);
            matchResults.put(rank, matchResults.get(rank) + 1);
        }
    }

    private int countMatch(List<Integer> lottoNumber, List<Integer> winningNumbers) {
        return (int) lottoNumber.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : matchResults.entrySet()) {
            totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
        }
        double rawProfitRate = (double) totalPrize / purchaseAmount;
        return Math.floor(rawProfitRate * 100) / 100.0;
    }

    public int getRankCount(Rank rank) {
        return matchResults.get(rank);
    }
}
