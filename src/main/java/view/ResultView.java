package view;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.Rank;

public class ResultView {

    public static void printLottos(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printStatistics(LottoResult result, Money amount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printRank(Rank.THREE, result);
        printRank(Rank.FOUR, result);
        printRank(Rank.FIVE, result);
        printRank(Rank.SIX, result);
        System.out.println(profitMessage(result.profitRate(amount)));
    }

    private static void printRank(Rank rank, LottoResult result) {
        System.out.printf("%d개 일치 (%d원)- %d개%n",
                rank.getMatchCount(), rank.getPrize(), result.countOf(rank));
    }

    private static String profitMessage(double rate) {
        String base = "총 수익률은 " + String.format("%.2f", rate) + "입니다.";
        if (rate < 1) {
            return base + "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        return base + "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    }
}
