package view;

import lotto.LottoRank;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.ProfitRate;
import lotto.PurchaseAmount;

import java.io.PrintStream;

public class ResultView {
    private final PrintStream output;

    public ResultView(PrintStream output) {
        this.output = output;
    }

    public void printLottoCount(int manualLottoCount, int automaticLottoCount) {
        output.println();
        output.println("수동으로 " + manualLottoCount + "장, 자동으로 "
                + automaticLottoCount + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        lottos.forEach(output::println);
    }

    public void printResult(LottoResult result, PurchaseAmount purchaseAmount) {
        output.println("당첨 통계");
        output.println("---------");
        printRanks(result);
        printProfitRate(result, purchaseAmount);
    }

    private void printRanks(LottoResult result) {
        LottoRank[] ranks = LottoRank.values();
        for (int index = ranks.length - 1; index >= 0; index--) {
            LottoRank rank = ranks[index];
            if (rank == LottoRank.MISS) {
                continue;
            }
            printRank(result, rank);
        }
    }

    private void printProfitRate(LottoResult result, PurchaseAmount purchaseAmount) {
        ProfitRate profitRate = result.calculateProfitRate(purchaseAmount);
        output.println("총 수익률은 " + profitRate
                + "입니다.(기준이 1이기 때문에 결과적으로 " + profitResult(profitRate) + "라는 의미임)");
    }

    private static String profitResult(ProfitRate profitRate) {
        if (profitRate.isProfit()) {
            return "이익이";
        }
        if (profitRate.isLoss()) {
            return "손해";
        }
        return "본전이";
    }

    private void printRank(LottoResult result, LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            output.println("5개 일치, 보너스 볼 일치(" + rank.prizeMoney()
                    + "원) - " + result.countOf(rank) + "개");
            return;
        }
        output.println(rank.matchCount() + "개 일치 (" + rank.prizeMoney()
                + "원)- " + result.countOf(rank) + "개");
    }

    public void printError(String errorMessage) {
        output.println(errorMessage);
    }
}
