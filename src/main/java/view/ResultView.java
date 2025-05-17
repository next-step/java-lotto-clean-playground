package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResult;
import domain.Lottos;
import domain.Number;
import domain.Numbers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ResultView {
    private static final String PURCHASED_COUNT = "%n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n";
    private static final String RESULT_TITLE = "당첨 통계";
    private static final String DIVIDER_LINE = "---------";
    private static final String RESULT_FORMAT = "%d개 일치%s(%d원)- %d개%n";
    private static final String BONUS_MATCH = ", 보너스 볼 일치";
    private static final String PROFIT_RATE = "총 수익률은 %.2f입니다.%n";

    public void printLottoCount(int manualCount, int autoCount) {
        System.out.printf(PURCHASED_COUNT, manualCount, autoCount);
    }

    public void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(lotto -> {
            Numbers numbers = lotto.getNumbers();
            List<Integer> numbersList = numbers.getNumbers().stream()
                    .map(Number::getNumber)
                    .sorted()
                    .toList();
            System.out.println(numbersList);
        });
    }

    public void printResult(LottoResult result) {
        System.out.println();
        System.out.println(RESULT_TITLE);
        System.out.println(DIVIDER_LINE);

        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.MISS)
                .sorted(Comparator.comparingInt(LottoRank::getPrize))
                .forEach(rank -> printRankResult(rank, result));

        System.out.printf(PROFIT_RATE, result.calculateProfitRate());
    }

    private void printRankResult(LottoRank rank, LottoResult result) {
        int count = result.getResult().getOrDefault(rank, 0);
        if (rank == LottoRank.SECOND) {
            System.out.printf(RESULT_FORMAT, rank.getMatchCount(), BONUS_MATCH, rank.getPrize(), count);
            return;
        }
        System.out.printf(RESULT_FORMAT, rank.getMatchCount(), "", rank.getPrize(), count);
    }
}
