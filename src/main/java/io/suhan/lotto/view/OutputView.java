package io.suhan.lotto.view;

import io.suhan.lotto.model.DrawResult;
import io.suhan.lotto.model.lotto.Lotto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static final Map<Integer, Integer> winningsMap = Map.of(
            3, 5000,
            4, 50000,
            5, 1500000,
            6, 2000000000
    );

    public static void printPurchaseResult(List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        printLottos(lottos);
    }

    public static void printDrawResult(List<DrawResult> results, int totalSpent) {
        Map<Integer, Long> countMap = calculateMatchedCounts(results);

        System.out.println("\n당첨 통계");
        System.out.println("---------");

        long totalWinnings = 0;

        for (int i = 3; i <= 6; i++) {
            long count = countMap.getOrDefault(i, 0L);
            int winnings = winningsMap.get(i);

            System.out.printf("%d개 일치 (%d원)- %d개\n", i, winnings, count);

            totalWinnings += (winnings * count);
        }

        double revenue = (double) totalWinnings / totalSpent;

        System.out.printf("총 수익률은 %.2f입니다.", revenue);
    }

    private static void printLottos(List<Lotto> lottos) {
        lottos.forEach(OutputView::printLotto);
    }

    private static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    private static Map<Integer, Long> calculateMatchedCounts(List<DrawResult> results) {
        Map<Integer, Long> countMap = new HashMap<>();

        for (DrawResult result : results) {
            int matchedCount = result.getMatchedCount();
            countMap.put(matchedCount, countMap.getOrDefault(matchedCount, 0L) + 1);
        }

        return countMap;
    }
}
