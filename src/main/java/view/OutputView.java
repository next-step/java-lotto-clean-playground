package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rank;
import domain.WinningStatistics;
import java.util.Arrays;

public class OutputView {

    private static final int MIN_PROFIT_RATE = 1;

    private OutputView() {
    }

    public static void printPurchaseCount(final int count) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public static void printLottos(final Lottos lottos) {
        for (Lotto lotto : lottos.getValues()) {
            System.out.println(
                    lotto.getNumbers().stream()
                            .map(LottoNumber::number)
                            .toList()
            );
        }
    }

    public static void printWinningStatistics(final WinningStatistics statistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .forEach(rank -> System.out.printf(
                        "%d개 일치 (%d원)- %d개%n",
                        rank.getMatchCount(),
                        rank.getPrize(),
                        statistics.getCount(rank)
                ));
    }

    public static void printProfitRate(final double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
        if (profitRate < MIN_PROFIT_RATE) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        System.out.println();
    }
}
