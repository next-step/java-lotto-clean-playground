package view;

import domain.*;

public class OutputView {

    public static void printLottoHistory(Lottos history) {
        System.out.println();
        System.out.println(history.size() + "개를 구매했습니다.");
        for (Lotto lotto : history.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printWinningResult(LottoResult result, int totalTickets) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        StringBuilder builder = new StringBuilder();
        appendWinningStatistics(builder, result);
        appendProfitRate(builder, result, totalTickets);

        System.out.println(builder);
    }

    private static void appendWinningStatistics(StringBuilder builder,  LottoResult result) {
        int fourth = result.fourthPrizeCount();
        int third  = result.thirdPrizeCount();
        int second = result.secondPrizeCount();
        int first  = result.firstPrizeCount();

        builder.append("3개 일치 (").append(Prize.FOURTH.getPrizeAmount()).append("원)- ").append(fourth).append("개\n");
        builder.append("4개 일치 (").append(Prize.THIRD.getPrizeAmount()).append("원)- ").append(third).append("개\n");
        builder.append("5개 일치 (").append(Prize.SECOND.getPrizeAmount()).append("원)- ").append(second).append("개\n");
        builder.append("6개 일치 (").append(Prize.FIRST.getPrizeAmount()).append("원)- ").append(first).append("개\n");
    }

    private static void appendProfitRate(StringBuilder builder, LottoResult result, int totalTickets) {
        long revenue = LottoProfitCalculator.calculateRevenue(result);
        int spent = LottoProfitCalculator.calculateTotalSpent(totalTickets);
        double rate = LottoProfitCalculator.calculateProfitRate(revenue, spent);

        String resultText = determineProfitResult(rate);
        builder.append(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)%n", rate, resultText));
    }

    private static String determineProfitResult(double rate) {
        if (rate >= 1) {
            return "이익";
        }
        return "손해";
    }

}
