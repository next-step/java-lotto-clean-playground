package view;

import domain.Lotto;
import domain.Lottos;
import domain.MatchResult;
import domain.WinningLotto;
import java.util.List;
import java.util.Map;

public class ResultView {

    public void printAllLottos(List<Lotto> allLottos, int manualCount) {
        int autoCount = allLottos.size() - manualCount;
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
        for (Lotto lotto : allLottos) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void printWinningLottoStatistics(int purchaseAmount, Lottos lottos, WinningLotto winningLotto) {
        Map<MatchResult, Integer> resultMap = MatchResult.of(lottos, winningLotto);

        System.out.println("당첨 통계");
        System.out.println("-----------");

        for (MatchResult result : MatchResult.values()) {
            if (result == MatchResult.MISS) {
                continue;
            }
            System.out.println(
                    result.getLabel() + " (" + result.getMatchReward() + "원)- " + resultMap.get(result) + "개");
        }

        double profitRate = MatchResult.getProfitRate(resultMap, purchaseAmount);
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
    }
}