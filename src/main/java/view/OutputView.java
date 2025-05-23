package view;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.money.Money;
import domain.rank.Rank;
import domain.rank.WinningStatistics;
import java.math.BigDecimal;
import java.util.Arrays;

public class OutputView {

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
                        formatRankMessage(rank),
                        rank.getMatchCount(),
                        rank.getPrize().amount(),
                        statistics.getCount(rank)
                ));
    }

    private static String formatRankMessage(final Rank rank) {
        if (rank == Rank.SECOND) {
            return "%d개 일치, 보너스 볼 일치 (%,.0f원) - %d개%n";
        }
        return "%d개 일치 (%,.0f원) - %d개%n";
    }


    public static void printProfitRate(final Money profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate.amount());
        validateStandardProfitRate(profitRate);
        System.out.println();
    }

    private static void validateStandardProfitRate(final Money profitRate) {
        boolean isStandardProfitRate = profitRate.amount().compareTo(BigDecimal.ONE) < 0;
        if (isStandardProfitRate) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
