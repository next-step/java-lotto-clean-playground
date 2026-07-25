package view;

import domain.Lotto;
import domain.Rank;
import domain.WinningStatistics;
import java.util.Arrays;
import java.util.Map;

public class ResultView {
    public static void printPurchaseAmount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public static void printStatistics(WinningStatistics statistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        Map<Rank, Integer> statisticsMap = statistics.getStatistics();
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .forEach(rank -> printRank(rank, statisticsMap));
    }

    private static void printRank(Rank rank, Map<Rank, Integer> statisticsMap) {
        int count = statisticsMap.getOrDefault(rank, 0);
        System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- " + count + "개");
    }
}

