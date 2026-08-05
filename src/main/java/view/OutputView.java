package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoRank;
import domain.LottoStatistics;
import domain.Lottos;
import domain.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public void printPurchasedCount(int manualLottoCount, int autoLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    public void printLottoStatistics(PurchaseAmount purchaseAmount, LottoStatistics lottoStatistics) {
        printStatisticsHeader();

        printWinningCounts(lottoStatistics);

        printProfitRate(lottoStatistics, purchaseAmount);
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numberValues = new ArrayList<>();

        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            numberValues.add(lottoNumber.getNumber());
        }

        System.out.println(numberValues);
    }

    private void printStatisticsHeader() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private void printWinningCounts(LottoStatistics lottoStatistics) {
        for (LottoRank rank : LottoRank.values()) {
            printWinningResult(lottoStatistics, rank);
        }
    }

    private void printProfitRate(LottoStatistics lottoStatistics, PurchaseAmount purchaseAmount) {
        System.out.println("총 수익률은 " + lottoStatistics.calculateProfitRate(purchaseAmount) + "입니다.");
    }

    private void printWinningResult(LottoStatistics lottoStatistics, LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            System.out.println(rank.getMatchCount() + "개 일치, 보너스 볼 일치("
                    + rank.getPrize() + "원) - "
                    + lottoStatistics.getWinningCount(rank) + "개");
            return;
        }
        System.out.println(rank.getMatchCount() + "개 일치 ("
                + rank.getPrize() + "원)- "
                + lottoStatistics.getWinningCount(rank) + "개");
    }
}
