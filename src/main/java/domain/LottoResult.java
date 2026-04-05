package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> matchResults;
    private final int bonusNumber;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int HUNDRED = 100;

    public LottoResult(LottoTickets lottoTickets, Lotto winningLotto, int bonusNumber,LottoTickets manualLottoTickets) {
        this.matchResults = new EnumMap<>(Rank.class);
        this.bonusNumber = bonusNumber;
        initResults();
        calculate(lottoTickets.getLottoNumber(), winningLotto.getNumbers(), this.bonusNumber, manualLottoTickets.getLottoNumber());
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            matchResults.put(rank, ZERO);
        }
    }

    private void calculate(List<Lotto> lottoNumber, List<Integer> winningNumbers, int bonusNumber,List<Lotto> manullottoNumber) {
        lottoNumber.forEach(lotto -> updateMatchResult(lotto, winningNumbers, bonusNumber));
        manullottoNumber.forEach(lotto -> updateMatchResult(lotto, winningNumbers, bonusNumber));

    }

    private void updateMatchResult(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = countMatch(lotto.getNumbers(), winningNumbers);
        boolean matchBonus = lotto.getNumbers().contains(bonusNumber);
        Rank rank = Rank.valueOf(matchCount, matchBonus);
        matchResults.put(rank, matchResults.get(rank) + ONE);
    }

    private int countMatch(List<Integer> lottoNumber, List<Integer> winningNumbers) {
        return (int) lottoNumber.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = ZERO;
        for (Map.Entry<Rank, Integer> entry : matchResults.entrySet()) {
            totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
        }
        double rawProfitRate = (double) totalPrize / purchaseAmount;
        return Math.floor(rawProfitRate * HUNDRED) / 100.0;
    }

    public int getRankCount(Rank rank) {
        return matchResults.get(rank);
    }
}