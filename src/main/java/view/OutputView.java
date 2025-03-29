package view;

import domain.*;

import java.util.Map;

public class OutputView {

    public static void printPurchaseResult(Integer lottoCount, LottoList lottoList) {
        System.out.printf("%n%d개를 구매했습니다.%n", lottoCount);

        for (Lotto lotto : lottoList.getLottoList()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningStatisticsMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printWinningStatistics(LottoStatistics lottoStatistics, PrizeCalculator prizeCalculator) {
        Map<Integer, Integer> matchCountMap = lottoStatistics.getMatchCountMap();

        // 당첨 통계 출력
        for (int i = 3; i <= 6; i++) {
            int count = matchCountMap.getOrDefault(i, 0);
            int prize = prizeCalculator.getPrize(i);
            System.out.printf("%d개 일치 (%d원)- %d개%n", i, prize, count);
        }
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.%n", profitRate);
    }
}
