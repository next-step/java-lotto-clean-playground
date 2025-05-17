package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResult;
import domain.Numbers;
import java.util.Collections;
import java.util.List;

public class ResultView {
    private static final String PURCHASED_COUNT = "%d개를 구매했습니다.%n";
    private static final String RESULT_TITLE = "당첨 통계";
    private static final String DIVIDER_LINE = "---------";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개%n";

    public void printLottoCount(int count) {
        System.out.printf(PURCHASED_COUNT, count);
    }

    public void printLottoNumbers(Lotto lotto) {
        Numbers numbers = lotto.getNumbers();
        List<Integer> numbersList = numbers.getNumbers();
        Collections.sort(numbersList);
        System.out.println(numbersList);
    }

    public void printResult(LottoResult result) {
        System.out.println();
        System.out.println(RESULT_TITLE);
        System.out.println(DIVIDER_LINE);
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.MISS) {
                continue;
            }
            int count = result.getResult().getOrDefault(rank, 0);
            System.out.printf(RESULT_FORMAT, rank.getMatchCount(), rank.getPrize(), count);
        }
    }
}
