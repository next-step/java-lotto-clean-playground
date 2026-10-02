package view;

import domain.Lotto;
import domain.LottoResult;
import domain.Rank;

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
        for (Rank rank : List.of(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)) {
            printRank(result, rank);
        }
        System.out.printf("총 수익률은 %.2f입니다.%n", result.calculateReturnRate(lottoCount));
    }

    private static void printRank(LottoResult result, Rank rank) {
        if (rank == Rank.SECOND) {
            System.out.printf("5개 일치, 보너스 볼 일치(%d원) - %d개%n",
                    rank.getPrize(), result.getCount(rank));
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개%n", rank.getMatchCount(), rank.getPrize(), result.getCount(rank));
    }
}
