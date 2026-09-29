package view;

import domain.purchase.Lotto;
import domain.purchase.Lottos;
import domain.winning.LottoRank;
import domain.winning.LottoResult;
import java.util.List;

public class OutputView {
    public void printLottos(Lottos lottos) {
        List<Lotto> lotto = lottos.getLottos();
        int count = lotto.size();
        System.out.println(count + "개를 구매했습니다.");

        for (int i = 0; i < count; i++) {
            printLotto(lotto.get(i));
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.MISS) {
                continue;
            }

            System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() +"원)- " + result.countOf(rank) + "개");
        }
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.");
    }
}
