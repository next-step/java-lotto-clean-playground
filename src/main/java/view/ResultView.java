package view;

import domain.Lotto;
import domain.LottoResult;

import java.util.List;

public class ResultView {

    public static void printLottoResult(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getSortedNumbers());
        }
    }

    public static void printStats(LottoResult result, int lottoCount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (int matchCount = 3; matchCount <= 6; matchCount++) {
            printRank(result, matchCount);
        }
        System.out.printf("총 수익률은 %.2f입니다.%n",
                result.calculateReturnRate(lottoCount));
    }

    private static void printRank(LottoResult result, int matchCount) {
        System.out.printf("%d개 일치 (%d원)- %d개%n",
                matchCount, result.getPrize(matchCount), result.getCount(matchCount));
    }
}
