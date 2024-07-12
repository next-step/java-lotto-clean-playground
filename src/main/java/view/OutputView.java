package view;

import global.Rank;

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

        List<Rank> ranks = List.of(Rank.FIFTH_PLACE, Rank.FOURTH_PLACE, Rank.THIRD_PLACE, Rank.SECOND_PLACE, Rank.FIRST_PLACE);
        for (Rank rank : ranks) {
            System.out.println(getPlacePhrase(lottoResult, rank));
        }

        System.out.printf("총 수익률은 %.2f입니다", rateOfReward);
    }

    private static String getPlacePhrase(final Map<Rank, Integer> lottoResult, final Rank rank) {
        if (rank.hasBonusBall()) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원)- %d개", rank.getCorrectCnt(), rank.getReward(), lottoResult.get(rank));
        }

        return String.format("%d개 일치 (%d원)- %d개", rank.getCorrectCnt(), rank.getReward(), lottoResult.get(rank));
    }
}
