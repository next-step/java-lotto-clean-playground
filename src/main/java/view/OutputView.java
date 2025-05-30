package view;

import domain.Lotto;
import domain.LottoList;
import domain.Prize;
import domain.WinningStatistics;

public class OutputView {
    public static void printLottoAmount(int cnt) {
        System.out.println(cnt + "개를 구매했습니다.");
    }

    public static void printLottoLists(LottoList lottoList) {
        for (Lotto list : lottoList.getLottoLists()) {
            System.out.println(list);
        }
    }

    public static void printWinningStatics(WinningStatistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("---------------------");
        for (Prize prize : Prize.values()) {
            int count = statistics.getPrizeCounts().getOrDefault(prize, 0);
            System.out.printf("%s (%d원)- %d개\n",
                    prize.getDescription(),
                    prize.getReward(),
                    count
            );
        }

        double rate = statistics.calculateRate();
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                rate, rate < 1.0 ? "손해" : "이득");
    }
}

