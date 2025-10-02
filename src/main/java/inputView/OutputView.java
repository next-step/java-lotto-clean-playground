package inputView;

import model.LottoNumbers;
import model.MatchResult;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printInputPrice() {
        System.out.print("구입 금액을 입력해 주세요: ");
    }

    public void printInvalidNumber() {
        System.out.println("숫자만 입력해 주세요");
    }

    public void printInvalidPrice() {
        System.out.println("0원 이상을 입력해 주세요");
    }

    public void printPriceValue(int price) {
        System.out.println(price);
    }

    public void printBuyCount(int manualCount, int autoCount) {
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
    }

    public void printInputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLottos(List<LottoNumbers> Lottos) {
        for (LottoNumbers lotto : Lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualLottoNumbersPrompt() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printLotteryStatistics(Map<MatchResult, Integer> matchCounts, String profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + matchCounts.get(MatchResult.THREE) + "개");
        System.out.println("4개 일치 (50000원)- " + matchCounts.get(MatchResult.FOUR) + "개");
        System.out.println("5개 일치 (150000원)- " + matchCounts.get(MatchResult.FIVE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)- " + matchCounts.get(MatchResult.FIVE_BONUS) + "개");
        System.out.println("6개 일치 (2000000000원)- " + matchCounts.get(MatchResult.SIX) + "개");
        System.out.println("총 수익률은 " + profitRate + "입니다.");
    }
}
