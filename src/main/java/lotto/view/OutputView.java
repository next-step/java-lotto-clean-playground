package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningResult;

public class OutputView {

    public void printPurchaseResult(
            int manualCount,
            int automaticCount,
            Lottos lottos
    ) {
        System.out.println();
        System.out.printf(
                "수동으로 %d장, 자동으로 %d개를 구매했습니다.%n",
                manualCount,
                automaticCount
        );

        for (Lotto lotto : lottos.getValues()) {
            System.out.println(lotto);
        }
    }

    public void printStatistics(
            WinningResult result,
            double profitRate
    ) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        printRanks(result);
        System.out.printf(
                "총 수익률은 %.1f%%입니다.%n",
                profitRate
        );
    }

    private void printRanks(WinningResult result) {
        printRank("3개 일치", Rank.FIFTH, result);
        printRank("4개 일치", Rank.FOURTH, result);
        printRank("5개 일치", Rank.THIRD, result);
        printRank(
                "5개 일치, 보너스 볼 일치",
                Rank.SECOND,
                result
        );
        printRank(
                "6개 일치",
                Rank.FIRST,
                result
        );
    }

    private void printRank(
            String matchMessage,
            Rank rank,
            WinningResult result
    ) {
        System.out.printf(
                "%s (%,d원) - %d개%n",
                matchMessage,
                rank.getPrize(),
                result.getCount(rank)
        );
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
