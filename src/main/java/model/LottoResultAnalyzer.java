package model;

import java.util.EnumMap;
import java.util.Map;

public class LottoResultAnalyzer {

    private final Map<Rank, Integer> result = new EnumMap<>(Rank.class);

    public LottoResultAnalyzer() {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
    }

    public void analyze(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottoTicket.getLottos()) {
            int matchCount = (int) lotto.getLottoNumbers()
                .stream()
                .filter(winningNumbers.getWinningLotto().getLottoNumbers()::contains)
                .count();

            boolean bonusMatched = lotto.getLottoNumbers().contains(winningNumbers.getBonusNumber());

            Rank rank = Rank.of(matchCount, bonusMatched);

            if (rank != null) {
                result.put(rank, result.getOrDefault(rank, 0) + 1);
            }
        }
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        long totalWinning = result.entrySet().stream()
            .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
            .sum();

        return (double) totalWinning / purchaseAmount.getAmount();
    }
}
