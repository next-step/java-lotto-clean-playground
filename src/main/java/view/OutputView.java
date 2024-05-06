package view;

import domain.Lotto;
import domain.LottoPrize;
import domain.LottoResult;

import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos, int manualLottoCount) {
        int autoLottoCount = lottos.size() - manualLottoCount;
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoCount, autoLottoCount);
        lottos.stream().skip(0).limit(manualLottoCount).forEach(System.out::println);
        lottos.stream().skip(manualLottoCount).limit(autoLottoCount).forEach(System.out::println);
    }

    public static void printResult(LottoResult result, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoPrize prize : LottoPrize.values()) {
            int count = result.getCountByPrize(prize);
            printPrizeStatics(prize, count);
        }
        double earningRate = result.getEarningRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.%n", earningRate);
    }

    private static void printPrizeStatics(LottoPrize prize, int statics) {
        if (prize == LottoPrize.MISS) {
            return;
        }
        if (prize.isMatchBonus()) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n", prize.getMatchCount(), prize.getPrize(), statics);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개%n", prize.getMatchCount(), prize.getPrize(), statics);
    }
}
