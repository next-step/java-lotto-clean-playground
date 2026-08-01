package view;

import lotto.LottoRank;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.ProfitRate;
import lotto.PurchaseAmount;

import java.util.Arrays;
import java.util.Comparator;

public class ResultView {
    public void printLottoCount(int manualLottoCount, int automaticLottoCount) {
        System.out.println();
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 "
                + automaticLottoCount + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        lottos.forEach(System.out::println);
    }

    public void printResult(LottoResult result, PurchaseAmount purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printRanks(result);
        printProfitRate(result, purchaseAmount);
    }

    private void printRanks(LottoResult result) {
        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.MISS)
                .sorted(Comparator.comparingLong(rank -> rank.prizeMoney().value()))
                .forEach(rank -> printRank(result, rank));
    }

    private void printProfitRate(LottoResult result, PurchaseAmount purchaseAmount) {
        ProfitRate profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.println("총 수익률은 " + profitRate
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
            System.out.println("5개 일치, 보너스 볼 일치(" + rank.prizeMoney()
                    + "원) - " + result.countOf(rank) + "개");
            return;
        }
        System.out.println(rank.matchCount() + "개 일치 (" + rank.prizeMoney()
                + "원)- " + result.countOf(rank) + "개");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
