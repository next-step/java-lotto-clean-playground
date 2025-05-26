package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;

public class LottoOutputView {

    public void printPurchasedLottoCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningStatistics(int money, Map<String, Long> winningLottos) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원) - " + winningLottos.get("3") + "개");
        System.out.println("4개 일치 (50000원) - " + winningLottos.get("4") + "개");
        System.out.println("5개 일치 (1500000원) - " + winningLottos.get("5") + "개");
        System.out.println("6개 일치 (2000000000원) - " + winningLottos.get("6") + "개");

        Long totalWinningMoney = winningLottos.get("total");

        double profitRate = (double) totalWinningMoney / money;
        System.out.printf("총 수익률은 %.2f%%입니다.%n", profitRate);
    }
}
