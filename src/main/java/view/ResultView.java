package view;
import domain.Lotto;
import domain.WinningLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ResultView {
    private static final int SINGLE_LOTTO_COUNT = 6;
    Lotto lotto = new Lotto();
    WinningLotto winningLotto = new WinningLotto();

    public void printAllLottos (final int purchaseAmount) {
        int lottoCount = lotto.getLottoCount(purchaseAmount);
        ArrayList<ArrayList<Integer>> allLottos = lotto.getAllLottos(purchaseAmount);

        System.out.println(lottoCount + "개를 구매했습니다.");

        for(List<Integer> lotto: allLottos) {
              System.out.println(lotto.toString());
        }
    }

    public void printWinningLottoStatistics (final int purchaseAmount, ArrayList<Integer> winningNumbers) {
        ArrayList<ArrayList<Integer>> allLottos = lotto.getAllLottos(purchaseAmount);
        HashMap<Integer, Integer> equalCountMap = winningLotto.checkEqualCount(allLottos, winningNumbers);


        System.out.println("당첨 통계");
        System.out.println("-----------");

        for (int key: equalCountMap.keySet()) {
            System.out.println(key + "개 일치-" + equalCountMap.get(key) + "개");
        }

        double profitRate = winningLotto.getLottoProfitRate(equalCountMap, purchaseAmount);
        System.out.println("총 수익률은 " + profitRate + "입니다.");
    }
}
