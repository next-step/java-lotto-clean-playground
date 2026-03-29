package view;
import domain.CalculateLottoNumber;
import domain.Lotto;
import domain.LottoNumber;
import domain.Rank;


public class OutputView {
    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static void printInputPurchaseAmount(){
        System.out.println("\n" + PURCHASE_AMOUNT_MESSAGE);
    }
    public static void printInputWinningNumber(){

        System.out.println("\n" + LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }

    public static void printLottos(LottoNumber lottoNumber,int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
        for (Lotto lotto : lottoNumber.getLottoNumber()) {
            System.out.println(lotto.getNumbers());
        }

    }
    public static void printWinningStatistics(CalculateLottoNumber result, int purchaseAmount) {
        System.out.println("\n당첨 통계\n---------");

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue; // 미당첨 내역은 출력에서 제외
            }
            System.out.printf("%d개 일치 (%d원)- %d개\n",
                    rank.getMatchCount(),
                    rank.getPrizeMoney(),
                    result.getRankCount(rank));
        }

        double profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", profitRate);
    }



}
