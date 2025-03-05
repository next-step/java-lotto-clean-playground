package view;

import model.LottoList;
import model.LottoPrize;
import model.LottoPrizeResult;

public class OutputView {
    public static void printLottoPurchases(LottoList lottoList, int manualLottoCount, int autoLottoCount) {
        System.out.println("\n수동으로 " + manualLottoCount + "개, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
        lottoList.getValue().forEach(lotto -> {
            System.out.println(lotto);
        });
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage + "\n");
    }

    public static void printLottoPrizeResult(LottoPrizeResult lottoPrizeResult) {
        System.out.println("\n당첨 통계\n---------");

        lottoPrizeResult.getValue().forEach((key, value) -> {
            if (key == LottoPrize.SECOND) {
                System.out.printf("%d개 일치, 보너스 볼 일치(%d원)- %d개\n", key.getMatchNumberCount(), key.getMoney(), value);
            } else {
                System.out.printf("%d개 일치 (%d원)- %d개\n", key.getMatchNumberCount(), key.getMoney(), value);
            }
        });

        System.out.printf("총 수익률은 %.2f입니다.", lottoPrizeResult.getProfitRate());
    }
}
