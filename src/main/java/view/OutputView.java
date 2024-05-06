package view;

import domain.Lotto;
import domain.LottoStatistics;

import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printStatistics(LottoStatistics statistics) {
        System.out.println("\n당첨 통계\n---------");
        System.out.printf("3개 일치 (%d원)- %d개\n", statistics.getPrizeFor(3), statistics.getCountFor(3));
        System.out.printf("4개 일치 (%d원)- %d개\n", statistics.getPrizeFor(4), statistics.getCountFor(4));
        System.out.printf("5개 일치 (%d원)- %d개\n", statistics.getPrizeFor(5), statistics.getCountFor(5));
        System.out.printf("6개 일치 (%d원)- %d개\n", statistics.getPrizeFor(6), statistics.getCountFor(6));
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                statistics.getProfitRatio(),
                statistics.getProfitRatio() < 1 ? "손해" : "이익");
    }
}
