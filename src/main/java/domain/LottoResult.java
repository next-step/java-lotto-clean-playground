package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> matchResults;
    private final int bonusNumber;

    public LottoResult(LottoTickets lottoTickets, Lotto winningLotto, int bonusNumber) {
        this.matchResults = new EnumMap<>(Rank.class);
        this.bonusNumber = bonusNumber;
        initResults();
        calculate(lottoTickets.getLottoNumber(), winningLotto.getNumbers(), this.bonusNumber);
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            matchResults.put(rank, 0);
        }
    }

    private void calculate(List<Lotto> lottoNumber, List<Integer> winningNumbers, int bonusNumber) {
        lottoNumber.forEach(lotto -> updateMatchResult(lotto, winningNumbers, bonusNumber));
    }

    private void updateMatchResult(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = countMatch(lotto.getNumbers(), winningNumbers);
        boolean matchBonus = lotto.getNumbers().contains(bonusNumber);
        Rank rank = Rank.valueOf(matchCount, matchBonus);
        matchResults.put(rank, matchResults.get(rank) + 1);
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