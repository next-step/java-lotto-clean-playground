package view;
import domain.Lotto;
import domain.MatchResult;
import domain.WinningLotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultView {

    Lotto lotto = new Lotto();
    WinningLotto winningLotto = new WinningLotto();

    public void printAllLottos (final int purchaseAmount, ArrayList<ArrayList<Integer>> allLottos) {
        int lottoCount = lotto.getLottoCount(purchaseAmount);

        System.out.println(lottoCount + "개를 구매했습니다.");

        for(List<Integer> lotto: allLottos) {
              System.out.println(lotto.toString());
        }
    }

    public void printWinningLottoStatistics (final int purchaseAmount, ArrayList<Integer> winningNumbers, ArrayList<ArrayList<Integer>> allLottos) {
        HashMap<MatchResult, Integer> resultMap = winningLotto.getMatchResult(allLottos, winningNumbers);

        System.out.println("당첨 통계");
        System.out.println("-----------");

        for (MatchResult result: MatchResult.values()) {
            int matchStandardCount = result.getMatchCount();
            if(matchStandardCount == 0) {
                continue;
            }

            int matchUnitReward = result.getMatchReward();
            int myMatchCount = resultMap.get(result);

            System.out.println(matchStandardCount + "개 일치 (" + matchUnitReward + "원)- " + myMatchCount + "개");
        }

        double profitRate = winningLotto.getLottoProfitRate(resultMap, purchaseAmount);
        // TODO: 여기 이상함!!!
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
    }
}
