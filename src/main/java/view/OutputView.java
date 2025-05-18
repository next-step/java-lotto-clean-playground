package view;

import domain.Lotto;
import domain.LottoHistory;
import domain.LottoProfitCalculator;
import domain.LottoWinningChecker;

import java.util.*;

public class OutputView {
    private static final long FIRST_PRIZE_AMOUNT = 2_000_000_000L;
    private static final long SECOND_PRIZE_AMOUNT = 1_500_000L;
    private static final long THIRD_PRIZE_AMOUNT = 50_000L;
    private static final long FOURTH_PRIZE_AMOUNT = 5_000L;

    public static void printLottoHistory(LottoHistory history) {
        System.out.println();
        System.out.println(history.size() + "개를 구매했습니다.");
        for (Lotto lotto : history.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printWinningResult(LottoWinningChecker checker, int totalTickets) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        StringBuilder builder = new StringBuilder();
        appendWinningStatistics(builder, checker);
        appendProfitRate(builder, checker, totalTickets);

        System.out.println(builder);
    }

    private static void appendWinningStatistics(StringBuilder builder, LottoWinningChecker checker) {
        int first = checker.getFirstPrize();
        int second = checker.getSecondPrize();
        int third = checker.getThirdPrize();
        int fourth = checker.getFourthPrize();

        builder.append("3개 일치 (").append(FOURTH_PRIZE_AMOUNT).append("원)- ").append(fourth).append("개\n");
        builder.append("4개 일치 (").append(THIRD_PRIZE_AMOUNT).append("원)- ").append(third).append("개\n");
        builder.append("5개 일치 (").append(SECOND_PRIZE_AMOUNT).append("원)- ").append(second).append("개\n");
        builder.append("6개 일치 (").append(FIRST_PRIZE_AMOUNT).append("원)- ").append(first).append("개\n");
    }

    private static void appendProfitRate(StringBuilder builder, LottoWinningChecker checker, int totalTickets) {
        long revenue = LottoProfitCalculator.calculateRevenue(checker);
        int spent = LottoProfitCalculator.calculateTotalSpent(totalTickets);
        double rate = LottoProfitCalculator.calculateProfitRate(revenue, spent);

        String result = determineProfitResult(rate);
        builder.append(String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)%n", rate, result));
    }

    private static String determineProfitResult(double rate) {
        if (rate >= 1) {
            return "이익";
        }
        return "손해";
    }

}
