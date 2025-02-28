package view;

import domain.*;

public class LottoOutputView implements LottoView {

    public void printLottoAmount(LottoCount lottoCount) {
        printEmptyLine();
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(lotto -> System.out.println(lotto.getLottoNumbers()));
        printEmptyLine();
    }

    public void printStatistics(LottoStatistics statistics, Amount purchaseAmount) {
        printStatisticsHeader();
        printWinningRanks(statistics);
        printProfitRate(statistics, purchaseAmount);
    }

    private void printStatisticsHeader() {
        printEmptyLine();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printWinningRanks(LottoStatistics statistics) {
        WinningRank[] orderedRanks = {
                WinningRank.THREE_MATCH,
                WinningRank.FOUR_MATCH,
                WinningRank.FIVE_MATCH,
                WinningRank.FIVE_MATCH_WITH_BONUS,
                WinningRank.SIX_MATCH
        };

        for (WinningRank rank : orderedRanks) {
            printWinningRank(rank, statistics);
        }
    }

    private void printWinningRank(WinningRank rank, LottoStatistics statistics) {
        int count = statistics.getStatistics().getOrDefault(rank, 0);

        if (rank == WinningRank.FIVE_MATCH_WITH_BONUS) {
            System.out.printf("5개 일치, 보너스 볼 일치 (%d원) - %d개%n", rank.getPrice(), count);
            return;
        }

        System.out.printf("%d개 일치 (%d원) - %d개%n", rank.getMatchCount(), rank.getPrice(), count);
    }

    private void printProfitRate(LottoStatistics statistics, Amount purchaseAmount) {
        double profitRate = statistics.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s 의미임)%n",
                profitRate, profitRate < 1 ? "손해라는" : "이득이라는");
    }

}
