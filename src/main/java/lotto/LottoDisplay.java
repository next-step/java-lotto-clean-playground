package lotto;

import java.util.Arrays;
import java.util.List;

public class LottoDisplay {
    private static void displayRank(LottoResult res, int count) {
        String label = res.getMatchingCount() + "개 일치";
        if (res == LottoResult.BONUS) {
            label = "5개 일치, 보너스 볼 일치";
        }
        System.out.printf("%s (%d원)- %d\n", label, res.getReward(), count);
    }

    public void displayPurchaseResult(int manualCount, int autoCount) {
        System.out.printf("\n수동 %d장, 자동 %d개를 구매했습니다.\n", manualCount, autoCount);
    }

    public void displayReceiptInfo(LottoReceipt receipt, int change) {
        receipt.lottos().getLottos().forEach(lotto -> {
            List<String> s = lotto.numbers().stream().map(Object::toString).toList();
            System.out.println("[" + String.join(", ", s) + "]");
        });
        if (change > 0) {
            System.out.println(change + "원이 남았습니다.");
        }
    }

    public void displayResult(LottoDraw draw) {
        System.out.println("\n당첨 통계\n---------");

        Arrays.stream(LottoResult.values())
                .filter(LottoResult::isDisplayable)
                .forEach(res -> displayRank(res, draw.getCount(res)));

        System.out.println("총 수익률은 " + draw.getRateOfReturn() + "입니다.");
    }

    public void displayError(String message) {
        System.out.println(message);
    }
}
