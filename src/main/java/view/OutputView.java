package view;

import domain.Lotto;
import domain.LottoResult;
import domain.LottoTickets;
import domain.Rank;

import java.util.stream.Collectors;

public class OutputView {
    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_TRIAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_TICKETS ="수동으로 구매할 번호를 입력해 주세요.";
    public static void printInputPurchaseAmount() {
        System.out.println("\n" + PURCHASE_AMOUNT_MESSAGE);
    }

    public static void printInputWinningNumber() {
        System.out.println("\n" + LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }

    public static void printManualLottoTickets() {
        System.out.println("\n" + MANUAL_LOTTO_TICKETS);
    }


    public static void printManualTrialCount() {
        System.out.println("\n" + MANUAL_TRIAL_COUNT);
    }

    public static void printLottoNumber(LottoTickets lottoNumber, int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
        for (Lotto lotto : lottoNumber.getLottoNumber()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printBonusNumber() {
        System.out.println("\n" + BONUS_NUMBER_MESSAGE);
    }

    public static void printWinningStatistics(LottoResult result, int purchaseAmount) {
        System.out.println("\n당첨 통계\n---------");
        System.out.print(generateRanksString(result));

        double profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", profitRate);
    }

    private static String generateRanksString(LottoResult result) {
        return Rank.getWinningRanks().stream()
                .map(rank -> rank.getMessage() + result.getRankCount(rank) + "개\n")
                .collect(Collectors.joining());
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}