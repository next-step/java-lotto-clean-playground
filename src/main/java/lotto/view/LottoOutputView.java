package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.LottoNumbers;
import lotto.model.Rank;

public class LottoOutputView {

    public void printPurchaseInfo(int manualCount, int autoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d장 구매했습니다.%n", manualCount, autoCount);
    }

    public void printLotto(List<LottoNumbers> lottoNumbers) {
        for (LottoNumbers lotto : lottoNumbers) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningStatistics(int money, Map<Rank, Long> winningLotto) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        long totalPrize = 0L;

        for (Rank rank : Rank.values()) {
            long count = winningLotto.getOrDefault(rank, 0L);
            System.out.println(rank.getDisplay() + " - " + count + "개");
            totalPrize += count * rank.getPrize();
        }

        double profitRate = (double) totalPrize / money;
        System.out.printf("총 수익률은 %.2f%%입니다.%n", profitRate);

    }
}
