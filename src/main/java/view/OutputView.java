package view;

import domain.lotto.LottoTicket;
import domain.lotto.ManualPurchaseCount;
import domain.lotto.PurchasedLottos;
import domain.money.PurchaseAmount;
import domain.result.LottoRank;
import domain.result.LottoStatistics;

public class OutputView {
    public void printPurchasedLottoTickets(
            PurchasedLottos purchasedLottoTickets,
            ManualPurchaseCount manualPurchaseCount
    ) {
        System.out.println();
        System.out.printf(
                "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n",
                manualPurchaseCount.value(),
                purchasedLottoTickets.size() - manualPurchaseCount.value()
        );
        purchasedLottoTickets.values().forEach(this::printLotto);
    }

    private void printLotto(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.values());
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
                .map(rank -> statisticsResultMessage(rank, lottoStatistics.countOf(rank)))
                .forEach(System.out::println);
    }

    private String statisticsResultMessage(LottoRank lottoRank, int count) {
        return matchMessage(lottoRank) + " (" + lottoRank.prizeAmount() + "원)- " + count + "개";
    }

    private String matchMessage(LottoRank lottoRank) {
        if (lottoRank.requiresBonusBallMatch()) {
            return lottoRank.matchCount() + "개 일치, 보너스 볼 일치";
        }
        return lottoRank.matchCount() + "개 일치";
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
