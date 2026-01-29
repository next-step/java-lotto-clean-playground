package view;

import domain.lotto.Lotto;
import domain.lotto.LottoResult;
import domain.lotto.Lottos;
import domain.lotto.Rank;

public class OutputView {

    public void printLottoPurchaseAmount() {
        print("구입금액을 입력해 주세요.");
    }

    public void printManualLottoCount() {
        printEmptyLine();
        print("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualLottoNumbers() {
        printEmptyLine();
        print("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printPurchasedLottos(int manualCount, int autoCount, Lottos lottos) {
        printEmptyLine();
        print("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            print(lotto.getLottoNumbers().toString());
        }
    }

    public void printWinningNumbersInput() {
        printEmptyLine();
        print("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberInput() {
        printEmptyLine();
        print("보너스 볼을 입력해 주세요.");
    }

    public void printResult(LottoResult result, int purchaseAmount) {
        printEmptyLine();
        print("당첨 통계");
        print("---------");
        printRankResult(Rank.FIFTH, result);
        printRankResult(Rank.FOURTH, result);
        printRankResult(Rank.THIRD, result);
        printSecondRankResult(result);
        printRankResult(Rank.FIRST, result);
        printProfitRate(result, purchaseAmount);
    }

    private void printRankResult(Rank rank, LottoResult result) {
        print(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- " + result.getCount(rank) + "개");
    }

    private void printSecondRankResult(LottoResult result) {
        print("5개 일치, 보너스 볼 일치(" + Rank.SECOND.getPrize() + "원) - " + result.getCount(Rank.SECOND) + "개");
    }

    private void printProfitRate(LottoResult result, int purchaseAmount) {
        double profitRate = result.calculateProfitRate(purchaseAmount);
        print(String.format("총 수익률은 %.2f입니다.", profitRate));
    }

    private void print(String value) {
        System.out.println(value);
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
