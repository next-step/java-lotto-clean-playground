package org.duckstudy.view;

import static org.duckstudy.model.lotto.constant.WinningRank.NONE;
import static org.duckstudy.model.lotto.constant.WinningRank.SECOND;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoNumber;
import org.duckstudy.model.lotto.LottoResult;
import org.duckstudy.model.lotto.constant.WinningRank;

public class OutputView {

    public static final int DEFAULT_VALUE = 0;

    public void printInputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLottos(List<Lotto> lottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottos.size());
        lottos.forEach(this::printLotto);
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
        iterateLottoResult(result.getResult());
    }

    private void iterateLottoResult(Map<Integer, Integer> result) {
        for (WinningRank winningRank : WinningRank.values()) {
            printMatchingResult(result, winningRank);
        }
        System.out.println();
    }

    private void printMatchingResult(Map<Integer, Integer> result, WinningRank winningRank) {
        if (winningRank == NONE) {
            return;
        }

        int cnt = winningRank.getMatchCount();
        int key = winningRank.getKey();
        int price = winningRank.getPrice();

        String matchPriceMessage = getMatchPrice(winningRank, cnt, price);
        Integer matchingCount = result.getOrDefault(key, DEFAULT_VALUE);

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
}
