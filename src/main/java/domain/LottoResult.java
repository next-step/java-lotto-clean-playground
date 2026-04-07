package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> matchResults;
    private final int bonusNumber;
    private static final int INITIAL_COUNT = 0;
    private static final int INCREMENT_COUNT = 1;

    public LottoResult(LottoTickets lottoTickets, Lotto winningLotto, int bonusNumber, LottoTickets manualLottoTickets) {
        this.matchResults = new EnumMap<>(Rank.class);
        this.bonusNumber = bonusNumber;
        initResults();
        calculate(lottoTickets.getLottoNumber(), winningLotto.getNumbers(), this.bonusNumber, manualLottoTickets.getLottoNumber());
    }

    private void initResults() {
        for (Rank rank : Rank.values()) {
            matchResults.put(rank, INITIAL_COUNT);
        }
    }

    private void calculate(List<Lotto> lottoNumber, List<Integer> winningNumbers, int bonusNumber, List<Lotto> manullottoNumber) {
        lottoNumber.forEach(lotto -> updateMatchResult(lotto, winningNumbers, bonusNumber));
        manullottoNumber.forEach(lotto -> updateMatchResult(lotto, winningNumbers, bonusNumber));
    }

    private void updateMatchResult(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        int matchCount = countMatch(lotto.getNumbers(), winningNumbers);
        boolean matchBonus = lotto.getNumbers().contains(bonusNumber);
        Rank rank = Rank.valueOfRank(matchCount, matchBonus);
        matchResults.put(rank, matchResults.get(rank) + INCREMENT_COUNT);
    }

    private int countMatch(List<Integer> lottoNumber, List<Integer> winningNumbers) {
        return (int) lottoNumber.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = 0L;
        for (Map.Entry<Rank, Integer> entry : matchResults.entrySet()) {
            totalPrize += (long) entry.getKey().getPrizeMoney() * entry.getValue();
        }
        return (double) totalPrize / purchaseAmount;
    }

    public int getRankCount(Rank rank) {
        return matchResults.get(rank);
    }
}
