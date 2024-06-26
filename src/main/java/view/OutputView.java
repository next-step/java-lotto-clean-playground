package view;

import model.Lotto;
import model.LottoPurchaseMoney;
import model.LottoResult;
import model.Lottos;
import model.Rank;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER = "\n";

    public static void showLotto(final Lottos lottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottos.getBuyLottoCount());

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        for (Lotto lotto : lottos.getLottos()) {
            final List<Integer> numbers = lotto.getNumbers();
            stringJoiner.add(numbers.toString());
        }
        System.out.println(stringJoiner);
    }

    public static void showResult(final LottoResult lottoResult, final LottoPurchaseMoney purchaseMoney) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        final Map<Rank, Integer> result = lottoResult.getResult();
        List<Rank> ranks = List.of(Rank.FOURTH_PLACE, Rank.THIRD_PLACE, Rank.SECOND_PLACE, Rank.FIRST_PLACE);
        for (Rank rank : ranks) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCorrectCnt(), rank.getReward(), result.get(rank));
        }

        System.out.printf("총 수익률은 %.2f입니다", lottoResult.getRateOfReturn(purchaseMoney.getValue()));
    }
}
