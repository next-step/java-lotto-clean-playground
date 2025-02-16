package view;

import java.util.Map;

import domain.LottoResult;
import domain.Lottos;
import enumerate.LottoRateEnum;

public class ResultView {

    public static void outputLotto(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.getCount() + "개를 구매했습니다.");
        System.out.println(lottos);
    }

    public static void outputWinningStatistics(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printWinningResults(lottoResult.getMatchCounts());
        printRateOfReturn(lottoResult.getRateOfReturn());
    }

    private static void printWinningResults(Map<LottoRateEnum, Integer> lottoRate) {
        for (LottoRateEnum lottoRateEnum : LottoRateEnum.values()) {
            int count = lottoRate.getOrDefault(lottoRateEnum, 0);
            System.out.println(lottoRateEnum.getFormattedRank(count));
        }
    }

    private static void printRateOfReturn(double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.%n", rateOfReturn);
    }
}
