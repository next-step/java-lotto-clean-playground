package view;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import model.Lotto;
import model.Rank;

public class OutputHandler {

    public static void printStatistics(Map<Rank, Integer> result, double rate) {
        System.out.println("당첨 통계\n---------");

        result.entrySet().stream()
                .filter(entry -> entry.getKey() != Rank.NONE)
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(Rank::getOrder)))
                .forEach(entry -> {
                    Rank rank = entry.getKey();
                    System.out.printf("%d개 일치 (%d원) - %d개%n", rank.getMatchCount(), rank.getPrize(), entry.getValue());
                });

        System.out.printf("총 수익률은 %.2f입니다.%n", rate);
    }

    public static void printPurchaseResult(int manualCount, int autoCount, List<Lotto> lottos) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualCount, autoCount);
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

}

