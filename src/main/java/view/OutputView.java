package view;

import java.util.List;
import java.util.Map;

import domain.FindWinningLotto;

public class OutputView {

    public static void printResultOfBuying(int numberOfManualLottos, int numberOfAutoLottos) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", numberOfManualLottos, numberOfAutoLottos);
    }

    public static void printResultOfAutoLotto(List<List<Integer>> lottoCollection) {
        for (List<Integer> lotto : lottoCollection) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printLottoWinningStatistics(Map<FindWinningLotto.LottoRank, Integer> winningStatistics, double incomeRate) {
        System.out.println("당첨 통계:");

        for (FindWinningLotto.LottoRank rank : FindWinningLotto.LottoRank.values()) {
            int count = winningStatistics.getOrDefault(rank, 0);

            String matchDescription = rank == FindWinningLotto.LottoRank.FIVE_WITH_BONUS ? "5개 일치, 보너스 볼 일치" : rank.getMatchCount() + "개 일치";

            if (rank != FindWinningLotto.LottoRank.NONE) {
                System.out.printf("%s (%d원)- %d개%n", matchDescription, rank.getPrize(), count);
            }
        }

        System.out.printf("총 수익률은 %.2f입니다.%n", incomeRate);
    }
}
