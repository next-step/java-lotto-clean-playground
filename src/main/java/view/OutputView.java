package view;

import java.text.DecimalFormat;
import java.util.Map;

import model.*;

public class OutputView {

    private static final DecimalFormat df = new DecimalFormat("0.##");

    public void printPurchaseCount(ManualCount manualCount, AutoCount autoCount) {
        System.out.println();
        System.out.printf("수동으로 %s장, 자동으로 %s개를 구매했습니다. \n", manualCount, autoCount);
    }

    public void printLottoNumbers(LottoTicket lottoTicket) {
        for (Lotto lotto : lottoTicket.getLottos()) {
            System.out.println(lotto);
        }
    }

    public void printStatistics(Map<Rank, Integer> result, double profitRate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.values()) {
            int count = result.getOrDefault(rank, 0);

            if (rank == Rank.SECOND) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%s원) - %d개%n",
                    rank.getMatchCount(),
                    String.format("%,d", rank.getPrize()),
                    count);
            } else {
                System.out.printf("%d개 일치 (%s원) - %d개%n",
                    rank.getMatchCount(),
                    String.format("%,d", rank.getPrize()),
                    count);
            }
        }

        System.out.printf("총 수익률은 %s입니다.%n", df.format(profitRate));
    }
}
