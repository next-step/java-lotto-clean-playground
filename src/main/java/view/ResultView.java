package view;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.Rank;

public class ResultView {

    public static void printLottos(Lottos lottos, int manualCount) {
        int autoCount = lottos.size() - manualCount;
        System.out.println();
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printStatistics(LottoResult result, Money amount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printRank(Rank.FIFTH, result);
        printRank(Rank.FOURTH, result);
        printRank(Rank.THIRD, result);
        printRank(Rank.SECOND, result);
        printRank(Rank.FIRST, result);
        System.out.println(profitMessage(result.profitRate(amount)));
    }

    private static void printRank(Rank rank, LottoResult result) {
        System.out.printf("%s (%d원) - %d개%n",
                matchText(rank), rank.getPrize(), result.countOf(rank));
    }

    private static String matchText(Rank rank) {
        if (rank.isBonusRequired()) {
            return rank.getMatchCount() + "개 일치, 보너스 볼 일치";
        }
        return rank.getMatchCount() + "개 일치";
    }

    private static String profitMessage(double rate) {
        String base = "총 수익률은 " + String.format("%.2f", rate) + "입니다.";
        if (rate < 1) {
            return base + "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        return base + "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    }
}
