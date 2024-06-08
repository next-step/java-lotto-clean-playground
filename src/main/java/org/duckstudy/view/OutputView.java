package org.duckstudy.view;

import static org.duckstudy.model.lotto.constant.WinningRank.NONE;
import static org.duckstudy.model.lotto.constant.WinningRank.SECOND;

import java.util.stream.Collectors;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoNumber;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.Lottos;
import org.duckstudy.model.lotto.constant.WinningRank;

public class OutputView {

    public void printInputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLottos(Lottos lottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottos.getSize());
        lottos.getLottos()
                .forEach(this::printLotto);
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.getLotto()
                .stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]")));
    }

    public void printInputWinningLotto() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printInputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
    }

    public void printLottoResult(LottoResult result) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        iterateLottoResult(result);
    }

    private void iterateLottoResult(LottoResult result) {
        for (WinningRank winningRank : WinningRank.values()) {
            printMatchingResult(result, winningRank);
        }
        System.out.println();
    }

    private void printMatchingResult(LottoResult result, WinningRank winningRank) {
        if (winningRank == NONE) {
            return;
        }

        int cnt = winningRank.getMatchCount();
        int key = winningRank.getKey();
        int price = winningRank.getPrice();

        String matchPriceMessage = getMatchPrice(winningRank, cnt, price);
        int matchingCount = result.getMatchingCount(key);

        System.out.println(matchPriceMessage + matchingCount + "개");
    }

    private String getMatchPrice(WinningRank winningRank, int cnt, int price) {
        if (winningRank == SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치(%d원)- ", cnt, price);
        }
        return String.format("%d개 일치 (%d원)- ", cnt, price);
    }

    public void printTotalProfit(double totalProfitRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", totalProfitRate);
    }

    public void printExceptionForBonusNumber() {
        System.out.println("보너스 볼은 당첨 번호와 중복되면 안됩니다.");
    }
}
