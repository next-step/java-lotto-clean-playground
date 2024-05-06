package View;
import Model.*;

import static View.InputData.bonus;
import static View.InputData.firstLotto;

public class ResultView {
    public static void printLottoResult(int hand, int auto, Lottos lottos) {
        System.out.println("수동으로 " + hand + "장, 자동으로 " + auto + "개를 구매했습니다.");
        lottos.getLottos()
                        .forEach(x -> System.out.println(x.getLottoNumbers()
                                .stream()
                                .map(LottoNumber::getNumber)
                                .sorted()
                                .toList()));

    }
    public static void finalResult(Lottos lottos) {
        printResults(lottos.matchList(firstLotto, bonus));
    }

    public static void printResults(int[] matchCounts) {
        for (DataEnum.LottoResult rank : DataEnum.LottoResult.values()) {
            int count = matchCounts[rank.ordinal()];
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getMatches(), rank.getReward(), count);
        }
        double result = (double)InputData.profit / InputData.inputMoney;
        System.out.printf("총 수익률은 %.2f입니다.\n",Math.floor(result * 100) / 100);
    }

}
