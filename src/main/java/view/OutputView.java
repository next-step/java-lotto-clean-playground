package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResult;
import domain.ManualTicketCount;
import java.util.Arrays;

public class OutputView {
    public void showLottoResults(LottoResult result, Double profitRate) {
        showLottoStatistics(result);
        showProfitRate(profitRate);
    }

    public void showLottoStatistics(LottoResult result) {
        System.out.println("\n당첨 통계\n---------");
        for (LottoRank lottoRank : Arrays.stream(LottoRank.values())
                .filter(rank -> !rank.equals(LottoRank.MISS))
                .toList()) {
            System.out.println(lottoRank.toString() + " - " + result.getMatchCount(lottoRank) + "개");
        }
    }

    public void showProfitRate(Double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
        if (profitRate > 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");
            return;
        }
        if (profitRate == 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 본전이라는 의미임)");
            return;
        }
        System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

    public void showLottoTickets(Lotto lotto, ManualTicketCount manualTicketCount) {
        System.out.println("\n수동으로 " + manualTicketCount.getCount() + "장, 자동으로 " + (lotto.getNumberOfTickets()
                - manualTicketCount.getCount()) + "장을 구매했습니다.");
        System.out.println(lotto);
        System.out.println();
    }
}
