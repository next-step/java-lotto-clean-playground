package view;

import domain.*;

import java.util.Map;

public class OutputView {

    public void printPurchaseResult(Integer lottoCount, LottoList lottoList) {
        System.out.printf("%n%d개를 구매했습니다.%n", lottoCount);

        for (Lotto lotto : lottoList.getLottoList()) {
            System.out.println(lotto.numbers());
        }
    }

    public void printWinningStatisticsMessage() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printWinningStatistics(LottoResult lottoStatistics) {
        Map<Prize, Integer> matchCountMap = lottoStatistics.getMatchCountMap();

        // 당첨 통계 출력
        for (Prize prize : Prize.values()) {
            if (prize == Prize.NONE) continue;
            int count = matchCountMap.getOrDefault(prize, 0);
            System.out.printf("%d개 일치%s (%d원) - %d개%n",
                    prize.getMatchCount(),
                    prize.isBonusMatch() ? " + 보너스볼" : "",
                    prize.getPrizeMoney(),
                    count);
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.%n", profitRate);
    }
}
