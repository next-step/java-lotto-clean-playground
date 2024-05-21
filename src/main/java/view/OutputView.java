package view;

import domain.Lotto;
import domain.LottoCalculator;

import java.util.List;

public class OutputView {
    public static final String START_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_MANUAL_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    public static final String INPUT_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static final String AMOUNT_MESSAGE_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    public static final String WINNING_STATISTICS_MESSAGE = "당첨 통계";
    public static final String WINNING_STATISTICS_FORMAT = "총 수익률은 %.2f 입니다.";

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printInputManualNumbersMessage() {
        System.out.println(INPUT_MANUAL_NUMBERS_MESSAGE);
    }

    public static void printInputManualCountMessage() {
        System.out.println(INPUT_MANUAL_COUNT_MESSAGE);
    }

    public static void printInputBonusBallMessage() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
    }

    public static void printInputWinningNumbersMessage() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
    }
    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printAmountMessage(int manualCount, int autoCount) {
        System.out.printf(AMOUNT_MESSAGE_FORMAT + "%n", manualCount, autoCount);
    }

    public static void printWinningStatistics(int[] matchCounts, double profitRate) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println("---------");
        for (LottoCalculator.Prize prize : LottoCalculator.Prize.values()) {
            int matchCount = prize.getMatchCount();
            if (matchCount == 7) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개%n", matchCount, prize.getAmount(), matchCounts[matchCount]);
            } else if (matchCount == 6) {
                System.out.printf("%d개 일치 (%d원)- %d개%n", matchCount, prize.getAmount(), matchCounts[matchCount]);
            } else {
                System.out.printf("%d개 일치 (%d원)- %d개%n", matchCount, prize.getAmount(), matchCounts[matchCount]);
            }
        }
        System.out.printf(WINNING_STATISTICS_FORMAT, profitRate);
        printProfitRate(profitRate);
    }

    public static void printProfitRate(double profitRate) {
        if (profitRate < 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        if (profitRate > 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 이득라는 의미임)");
        }
    }
}