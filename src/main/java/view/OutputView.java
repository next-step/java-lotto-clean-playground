package view;

import domain.purchase.Lotto;
import domain.purchase.Lottos;
import domain.winning.LottoRank;
import domain.winning.LottoResult;
import java.util.List;

public class OutputView {
    public void printLottos(int manualCount, int autoCount, Lottos lottos) {
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoRank rank : LottoRank.values()) {
            printRank(rank, result);
        }
    }

    private void printRank(LottoRank rank, LottoResult result) {
        if (rank == LottoRank.MISS) {
            return;
        }
        if (rank == LottoRank.SECOND) {
            System.out.println(rank.getMatchCount() + "개 일치, 보너스 볼 일치(" + rank.getPrize() + "원) - "
                    + result.countOf(rank) + "개");
            return;
        }
        System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- " + result.countOf(rank) + "개");
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.");
    }
}
