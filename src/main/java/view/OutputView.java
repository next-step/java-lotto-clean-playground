package view;

import domain.LottoResult;
import domain.LottoTicket;
import domain.Prize;

import java.util.List;

public class OutputView {
    public void printPurchaseLotto(int manualCount, int autoCount, List<LottoTicket> lottoTickets) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, autoCount);
        for (LottoTicket ticket : lottoTickets) {
            System.out.println(ticket);
        }
    }

    public void printLottoResult(LottoResult result, int purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        for (Prize prize : Prize.values()) {
            if (prize == Prize.SECOND) {
                System.out.printf("5개 일치, 보너스 볼 일치 (%d원) - %d개\n",
                        prize.getPrizeAmount(), result.getCount(prize));
            } else {
                System.out.printf("%d개 일치 (%d원) - %d개\n",
                        prize.getMatchCount(), prize.getPrizeAmount(), result.getCount(prize));
            }
        }
        double rateOfReturn = result.calculateRateOfReturn(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.\n", rateOfReturn);
    }
}
