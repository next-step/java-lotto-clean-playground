package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private static final int MIN_WINNING_MATCH_COUNT = 3;
    private static final int MAX_WINNING_MATCH_COUNT = 6;

    public void printPurchasedCount(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.calculateLottoCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    public void printLottoStatistics(Lottos lottos, WinningNumbers winningNumbers, PurchaseAmount purchaseAmount) {
        printStatisticsHeader();

        LottoStatistics lottoStatistics = new LottoStatistics(lottos, winningNumbers);
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
        for (int matchCount = MIN_WINNING_MATCH_COUNT; matchCount <= MAX_WINNING_MATCH_COUNT; matchCount++) {
            System.out.println(matchCount + "개 일치 ("
                    + lottoStatistics.getPrizeAmount(matchCount) + ")- "
                    + lottoStatistics.getWinningCount(matchCount) + "개");
        }
    }

    private void printProfitRate(LottoStatistics lottoStatistics, PurchaseAmount purchaseAmount) {
        System.out.println("총 수익률은 " + lottoStatistics.calculateProfitRate(purchaseAmount) + "입니다.");
    }
}
