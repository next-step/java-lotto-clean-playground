package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.LottoNumbers;
import lotto.model.Rank;

public class LottoOutputView {

    public void printPurchasedLottoCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<LottoNumbers> lottoNumbers) {
        for (LottoNumbers lotto : lottoNumbers) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningStatistics(int money, Map<Rank, Long> winningLottos) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        long totalPrize = 0L;

        for (Rank rank : Rank.values()) {
            long count = winningLottos.getOrDefault(rank, 0L);
            System.out.println(rank.getDisplay() + " - " + count + "개");
            totalPrize += count * rank.getPrize();
        }

        double profitRate = (double) totalPrize / money;
        System.out.printf("총 수익률은 %.2f%%입니다.%n", profitRate);

    }
}
