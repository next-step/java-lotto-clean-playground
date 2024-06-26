package view;

import common.Rank;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER = "\n";

    public static void showLotto(final List<List<Integer>> lottos, int buyCnt) {
        System.out.printf("\n%d개를 구매했습니다.\n", buyCnt);

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        for (List<Integer> lotto : lottos) {
            stringJoiner.add(lotto.toString());
        }
        System.out.println(stringJoiner);
    }

    public static void showResult(final Map<Rank, Integer> lottoResult, final double rateOfReward) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        List<Rank> ranks = List.of(Rank.FOURTH_PLACE, Rank.THIRD_PLACE, Rank.SECOND_PLACE, Rank.FIRST_PLACE);
        for (Rank rank : ranks) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCorrectCnt(), rank.getReward(), lottoResult.get(rank));
        }

        System.out.printf("총 수익률은 %.2f입니다", rateOfReward);
    }
}
