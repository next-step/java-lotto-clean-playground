package org.duckstudy.view;

import static org.duckstudy.model.Price.calculateWinningPrice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.LottoNumber;
import org.duckstudy.model.lotto.LottoResult;

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

    public void printWinningResult(LottoResult result) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        iterateWinningResult(result.getResult());
    }

    private void iterateWinningResult(Map<Integer, Integer> result) {
        for (int i = LottoResult.MIN_MATCHING_COUNT_TO_WIN; i <= LottoResult.MAX_MATCHING_COUNT_TO_WIN; i++) {
            printMatchingCount(result, i);
        }
        System.out.println();
    }

    private void printMatchingCount(Map<Integer, Integer> result, int cnt) {
        System.out.printf("%d개 일치 (%d원)- ", cnt, calculateWinningPrice(cnt).getValue());

        int count = result.getOrDefault(cnt, DEFAULT_VALUE);
        System.out.printf("%d개\n", count);
    }

    public void printTotalProfit(double totalProfitRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", totalProfitRate);
    }
}
