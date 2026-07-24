package view;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.money.PurchaseAmount;
import domain.result.LottoRank;
import domain.result.LottoStatistics;
import java.util.List;

public class OutputView {
    public void printPurchasedLottoTickets(List<Lotto> purchasedLottoTickets) {
        printPurchasedLottoTickets(new Lottos(purchasedLottoTickets));
    }

    public void printPurchasedLottoTickets(Lottos purchasedLottoTickets) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.%n", purchasedLottoTickets.size());
        purchasedLottoTickets.values().forEach(System.out::println);
    }

    public void printLottoStatistics(
            LottoStatistics lottoStatistics,
            PurchaseAmount purchaseAmount
    ) {
        printStatisticsHeader();
        printStatisticsResults(lottoStatistics);
        printProfitRate(lottoStatistics, purchaseAmount);
    }

    private void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printStatisticsResults(LottoStatistics lottoStatistics) {
        LottoRank.valuesForResult().stream()
                .map(rank -> rank.resultMessage(lottoStatistics.countOf(rank)))
                .forEach(System.out::println);
    }

    private void printProfitRate(
            LottoStatistics lottoStatistics,
            PurchaseAmount purchaseAmount
    ) {
        double profitRate = lottoStatistics.profitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
        printLossMessage(profitRate);
        System.out.println();
    }

    private void printLossMessage(double profitRate) {
        if (profitRate < 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
