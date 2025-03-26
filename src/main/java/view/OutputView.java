package view;

import domain.Lotto;
import domain.LottoList;
import domain.ProfitCalculator;

import java.util.Map;

public class OutputView {

    public static void printPurchaseResult(Integer lottoCount, LottoList lottoList) {
        System.out.println(lottoCount + "개를 구매했습니다.");

        for (Lotto lotto : lottoList.getLottoList()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningStatisticsMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printWinningStatistics(ProfitCalculator profitCalculator) {
        Map<Integer, Integer> matchCountMap = profitCalculator.getMatchCountMap();

        // 당첨 통계 출력
        for (int i = 3; i <= 6; i++) {
            int count = matchCountMap.getOrDefault(i, 0);
            int prize = ProfitCalculator.getPrize(i);
            System.out.printf("%d개 일치 (%d원)- %d개%n", i, prize, count);
        }
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n", profitRate);
    }
}
