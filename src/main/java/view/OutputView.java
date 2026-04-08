package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResult;

public class OutputView {
    public void showLottoResults(LottoResult result, Double profitRate) {
        showLottoStatistics(result);
        showProfitRate(profitRate);
    }

    public void showLottoStatistics(LottoResult result) {
        System.out.println("\n당첨 통계\n---------");
        for (LottoRank lottoRank : LottoRank.values()) {
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

    public void showLottoTickets(Lotto lotto) {
        System.out.println();
        System.out.println(lotto.getNumberOfTickets() + "개를 구매했습니다.");
        System.out.println(lotto);
        System.out.println();
    }
}
