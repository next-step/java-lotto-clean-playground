package view;
import domain.CalculateLottoNumber;
import domain.Lotto;
import domain.LottoNumber;
import domain.Rank;


public class OutputView {
    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static void printInputPurchaseAmount(){
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
    }
    public static void printInputWinningNumber(){
        System.out.println(LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }

    public static void printLottos(LottoNumber lottoNumber) {
        for (Lotto lotto : lottoNumber.getLottoNumber()) {
            System.out.println(lotto.getNumbers());
        }
    }
    public static void printWinningStatistics(CalculateLottoNumber result, int purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5000원)- %d개\n", result.getRankCount(Rank.THREE));
        System.out.printf("4개 일치 (50000원)- %d개\n", result.getRankCount(Rank.FOUR));
        System.out.printf("5개 일치 (1500000원)- %d개\n", result.getRankCount(Rank.FIVE));
        System.out.printf("6개 일치 (2000000000원)- %d개\n", result.getRankCount(Rank.SIX));

        double profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", profitRate);
    }



}
