package view;

import domain.*;

import java.util.List;

public class LottoOutputView {

    public void printLottoAmount(LottoCount lottoCount) {
        System.out.println();
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }

    public void printLottoPurchaseResult(LottoCount manualCount, LottoCount totalCount, Lottos lottos) {
        System.out.println();
        System.out.println("수동으로 " + manualCount.getCount() + "장, 자동으로 " + (totalCount.getCount() - manualCount.getCount()) + "개를 구매했습니다.");
        printLottos(lottos);
    }

    public void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(lotto -> System.out.println(lotto.getLottoNumbers()));
        System.out.println();
    }

    public void printStatistics(LottoStatistics statistics, LottoPurchaseAmount purchaseLottoPurchaseAmount) {
        printStatisticsHeader();
        printWinningRanks(statistics);
        printProfitRate(statistics, purchaseLottoPurchaseAmount);
    }

    private void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printWinningRanks(LottoStatistics statistics) {
        List<WinningRank> orderedRanks = WinningRank.getOrderedRanks();

        for (WinningRank rank : orderedRanks) {
            printWinningRank(rank, statistics);
        }
    }

    private void printWinningRank(WinningRank rank, LottoStatistics statistics) {
        int count = statistics.getStatistics().getOrDefault(rank, 0);

        if (rank == WinningRank.FIVE_MATCH_WITH_BONUS) {
            System.out.println("5개 일치, 보너스 볼 일치 (" + rank.getPrice() + "원) - " + count + "개");
            return;
        }

        System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrice() + "원) - " + count + "개");
    }

    private void printProfitRate(LottoStatistics statistics, LottoPurchaseAmount purchaseLottoPurchaseAmount) {
        double profitRate = statistics.calculateProfitRate(purchaseLottoPurchaseAmount);
        String message = "이득이라는";

        if (profitRate < 1) {
            message = "손해라는";
        }

        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다. (기준이 1이기 때문에 결과적으로 " + message + " 의미임)");
    }
}
