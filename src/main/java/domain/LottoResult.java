package domain;

import java.util.Map;

public class LottoResult {
    private LottoResult() {
        throw new AssertionError("LottoResult는 인스턴스화 할 수 없습니다.");
    }

    //로또 당첨 결과: Rank별 당첨 개수
    public static Prize calculateTotalPrize(MatchResult matchResults) {
        Prize totalPrize = Prize.from(0L);

        for (Map.Entry<Rank, Integer> entry : matchResults.getResult().entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize = totalPrize.add(rank.getPrize().multiply(count));
        }

        return totalPrize;
    }

    public static double calculateRateOfReturn(Prize totalPrize, int paidMoney) {
        return (double) totalPrize.getAmount() / paidMoney;
    }
}
