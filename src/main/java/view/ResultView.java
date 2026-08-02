package view;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.lotto.Money;
import domain.lotto.Rank;
import domain.lotto.WinningResult;

import java.util.Map;

public class ResultView {
    private ResultView() {
    }

    public static void printLottos(Lottos lottos, int manualCount) {
        int autoCount = lottos.size() - manualCount;
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n",
                manualCount, autoCount);

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(
                    lotto.getLottoNumbers().stream()
                            .map(LottoNumber::value)
                            .toList()
            );
        }
    }

    public static void printStatistics(WinningResult winningResult, Money purchasePrice) {
        System.out.println("당첨 통계\n---------");
        Map<Rank, Integer> result = winningResult.getStatistics();

        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            printEntry(entry);
        }

        double rate = winningResult.calculateProfitRate(purchasePrice);
        System.out.println("총 수익률은 " + String.format("%.2f", rate) + "입니다.");
    }

    private static void printEntry(Map.Entry<Rank, Integer> entry) {
        Rank rank = entry.getKey();

        if (rank == Rank.NONE) {
            return;
        }

        System.out.println(createMessage(rank, entry.getValue()));
    }

    private static String createMessage(Rank rank, int count) {
        if (rank.isBonusRequired()) {
            return rank.getMatchCount() + "개 일치, 보너스 볼 일치("
                    + rank.getPrice().value() + "원)- "
                    + count + "개";
        }

        return rank.getMatchCount() + "개 일치 ("
                + rank.getPrice().value() + "원)- "
                + count + "개";
    }
}
