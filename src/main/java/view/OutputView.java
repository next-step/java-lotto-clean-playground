package view;

import domain.Lotto;
import domain.LottoList;
import domain.Prize;
import domain.WinningStatistics;

public class OutputView {

    public static void printLottoLists(int manualCount, int autoCount, LottoList lottoList) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다\n", manualCount, autoCount);
        for (Lotto list : lottoList.getLottoLists()) {
            System.out.println(list);
        }
    }

    private static String formatPrizeLine(Prize prize, int count) {
        String text = prize.getMatchCount() + "개 일치";
        if (prize.isBonusStatus()) {
            text += ", 보너스 볼 일치";
        }
        return String.format("%s (%,d원) - %d개", text, prize.getReward(), count);
    }

    public static void printWinningStatics(WinningStatistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("---------------------");
        for (Prize prize : Prize.values()) {
            int count = statistics.getPrizeCounts().getOrDefault(prize, 0);
            System.out.println(formatPrizeLine(prize, count));
        }
        double rate = statistics.calculateRate();
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                rate, rate < 1.0 ? "손해" : "이득");
    }
}

