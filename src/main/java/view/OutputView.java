package view;

import domain.*;

public class OutputView {

    public void printPurchaseResult(LottoTickets tickets) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n",
                tickets.getManualCount(), tickets.getAutoCount());

        tickets.getTickets().forEach(System.out::println);
    }

    public void printResult(LottoResult result, Money purchaseAmount) {
        printResultHeader();
        printResultDetails(result);
        printEarningRate(result, purchaseAmount);
    }

    private void printResultHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }

    private void printResultDetails(LottoResult result) {
        result.getWinningRanks().forEach(rank -> {
            int count = result.getCountByRank(rank);
            System.out.printf("%s (%s) - %d개%n",
                    rank.getDescription(), rank.getPrizeText(), count);
        });
    }

    private void printEarningRate(LottoResult result, Money purchaseAmount) {
        long totalPrize = result.calculateTotalPrize();
        double earningRate = purchaseAmount.calculateEarningRate(totalPrize);

        System.out.printf("총 수익률은 %.2f입니다.", earningRate);

        if (earningRate > 1) {
            System.out.println("축하! 이익이 발생 !");
        }

        if (earningRate == 1) {
            System.out.println("본전임.");
        }

        if (earningRate < 1) {
            System.out.println("기준이 1이기 때문에 결과적으로 손해라는 의미임.");
        }
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
