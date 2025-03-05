package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Prize, Integer> prizeCountMap;

    public LottoResult() {
        this.prizeCountMap = new EnumMap<>(Prize.class);
        for (Prize prize : Prize.values()) {
            prizeCountMap.put(prize, 0);
        }
    }

    public void addPrize(Prize prize) {
        if (prize != null) {
            prizeCountMap.put(prize, prizeCountMap.get(prize) + 1);
        }
    }

    public int getCount(Prize prize) {
        return prizeCountMap.getOrDefault(prize, 0);
    }

    public double calculateRateOfReturn(int purchaseAmount) {
        if (purchaseAmount == 0) {
            return 0.0;
        }
        long totalPrize = 0;
        for (Prize prize : Prize.values()) {
            totalPrize += prize.getPrizeAmount() * prizeCountMap.get(prize);
        }

        return (double) totalPrize / purchaseAmount;
    }

    public static LottoResult calculateLottoResult(List<LottoTicket> tickets, List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        LottoResult result = new LottoResult();
        for (LottoTicket ticket : tickets) {
            int matchCount = ticket.countMatchingNumbers(winningNumbers);
            boolean hasBonus = ticket.containsNumber(bonusNumber);
            Prize prize = Prize.getPrize(matchCount, hasBonus);
            result.addPrize(prize);
        }
        return result;
    }
}
