package view;
import domain.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultView {
    private static final int SINGLE_LOTTO_COUNT = 6;
    Lotto lotto = new Lotto();

    public void printAllLottos (final int purchaseAmount) {
        int lottoCount = lotto.getLottoCount(purchaseAmount);
        ArrayList<ArrayList<Integer>> allLottos = lotto.getAllLottos(purchaseAmount);

        System.out.println(lottoCount + "개를 구매했습니다.");

        for(List<Integer> lotto: allLottos) {
              System.out.println(lotto.toString());
//            for(int i = 0 ; i < lottoCount; i++) {
//                System.out.println(lotto.get(i));
//            }
        }
    }
}
