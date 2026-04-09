package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.MatchResult;
import domain.WinningLotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultView {

    WinningLotto winningLotto = new WinningLotto();

    public void printAllLottos(List<Lotto> allLottos) {
        int lottoCount = allLottos.size();

        System.out.println(lottoCount + "개를 구매했습니다.");

        for (Lotto lotto : allLottos) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void printWinningLottoStatistics(final int purchaseAmount, List<LottoNumber> winningNumbers, List<Lotto> allLottos, LottoNumber bonusBall) {
        HashMap<MatchResult, Integer> resultMap = winningLotto.getMatchResult(allLottos, winningNumbers, bonusBall);

        System.out.println("당첨 통계");
        System.out.println("-----------");

        for (MatchResult result : MatchResult.values()) {
            int matchStandardCount = result.getMatchCount();
            if (matchStandardCount == 0) {
                continue;
            }

            int matchUnitReward = result.getMatchReward();
            int myMatchCount = resultMap.getOrDefault(result, 0);

            System.out.println(matchStandardCount + "개 일치 (" + matchUnitReward + "원)- " + myMatchCount + "개");
        }

        double profitRate = winningLotto.getLottoProfitRate(resultMap, purchaseAmount);
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
    }
}
