package view;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.money.PurchaseAmount;
import domain.result.LottoRank;
import domain.result.WinningStatistics;
import java.util.List;

public class OutputView {
    private static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.%n";
    private static final String STATISTICS_TITLE = "당첨 통계";
    private static final String STATISTICS_LINE = "---------";
    private static final String PROFIT_FORMAT =
            "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n";

    public void printPurchasedLottoTickets(List<Lotto> purchasedLottoTickets) {
        printPurchasedLottoTickets(new Lottos(purchasedLottoTickets));
    }

    public void printPurchasedLottoTickets(Lottos purchasedLottoTickets) {
        System.out.println();
        System.out.printf(PURCHASE_COUNT_FORMAT, purchasedLottoTickets.size());
        purchasedLottoTickets.values().forEach(System.out::println);
    }

    public void printWinningStatistics(
            WinningStatistics winningStatistics,
            PurchaseAmount purchaseAmount
    ) {
        printStatisticsHeader();
        printStatisticsResults(winningStatistics);
        printProfitRate(winningStatistics, purchaseAmount);
    }

    private void printStatisticsHeader() {
        System.out.println();
        System.out.println(STATISTICS_TITLE);
        System.out.println(STATISTICS_LINE);
    }

    private void printStatisticsResults(WinningStatistics winningStatistics) {
        LottoRank.valuesForResult().stream()
                .map(rank -> rank.resultMessage(winningStatistics.countOf(rank)))
                .forEach(System.out::println);
    }

    private void printProfitRate(
            WinningStatistics winningStatistics,
            PurchaseAmount purchaseAmount
    ) {
        System.out.printf(PROFIT_FORMAT, winningStatistics.profitRate(purchaseAmount));
    }
}
