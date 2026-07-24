package view;

import domain.LotteryStatistics;
import domain.Lotto;
import domain.Lottos;
import domain.Rank;

import java.util.Map;

public class ResultView {

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.\n");

        for(Lotto lotto : lottos) {
            System.out.println(lotto.getLottoNumbers());
        }
    }

    public void printStatistics(LotteryStatistics statistics, int totalPrize, int purchasePrice) {
        System.out.println("당첨 통계\n---------");
        Map<Rank, Integer> result = statistics.getStatistics();

        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            printEntry(entry);
        }

        double rate = (double) totalPrize / purchasePrice;
        System.out.println("총 수익률은 " + String.format("%.2f", rate) + "입니다.");
    }

    private void printEntry(Map.Entry<Rank, Integer> entry) {
        if (entry.getKey() == Rank.NONE) {
            return;
        }
        System.out.println(entry.getKey().getMatchCount() + "개 일치 ("
                + entry.getKey().getPrice() + "원)- "
                + entry.getValue() + "개");
    }
}
