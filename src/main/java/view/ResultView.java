package view;

import domain.LotteryStatistics;
import domain.Lotto;
import domain.Lottos;
import domain.Rank;
import domain.Money;

import java.util.Map;

public class ResultView {

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.\n");

        for(Lotto lotto : lottos) {
            System.out.println(lotto.getLottoNumbers());
        }
    }

    public void printStatistics(LotteryStatistics statistics, Money totalPrize, Money purchasePrice) {
        System.out.println("당첨 통계\n---------");
        Map<Rank, Integer> result = statistics.getStatistics();

        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            printEntry(entry);
        }

        double rate = totalPrize.divide(purchasePrice);
        System.out.println("총 수익률은 " + String.format("%.2f", rate) + "입니다.");
    }

    private void printEntry(Map.Entry<Rank, Integer> entry) {
        Rank rank = entry.getKey();

        if (rank == Rank.NONE) {
            return;
        }
        System.out.println(createMessage(rank, entry.getValue()));
    }

    private String createMessage(Rank rank, int count) {
        if (rank == Rank.SECOND) {
            return "5개 일치, 보너스 볼 일치("
                    + rank.getPrice() + "원)- "
                    + count + "개";
        }
        return rank.getMatchCount() + "개 일치 ("
                + rank.getPrice() + "원)- "
                + count + "개";
    }
}
