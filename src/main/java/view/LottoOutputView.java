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
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        for (WinningRank rank : WinningRank.values()) {
            printWinningRanks(rank, statistics);
        }

        double profitRate = statistics.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s 의미임)%n",
                profitRate, profitRate < 1 ? "손해라는" : "이득이라는");
    }

    private void printWinningRanks(WinningRank rank, LottoStatistics statistics) {
        int count = statistics.getStatistics().getOrDefault(rank, 0);
        System.out.printf("%d개 일치 (%d원) - %d개%n", rank.getMatchCount(), rank.getPrice(), count);
    }

}
