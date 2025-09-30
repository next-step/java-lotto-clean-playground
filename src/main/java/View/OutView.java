package View;

import Model.Lotto;
import Model.Lottos;

public class OutView {

    public void printLottoMatcher(int[] matchCounts, double rate) {
        System.out.println("당첨 통계");
        System.out.println("------");
        System.out.println("3개 일치(5,000원): " + matchCounts[3] + "개");
        System.out.println("4개 일치(50,000원): " + matchCounts[4] + "개");
        System.out.println("5개 일치(1,500,000원): " + matchCounts[5] + "개");
        System.out.println("6개 일치(2,000,000,000원): " + matchCounts[6] + "개");
        System.out.printf("총 수익률은 %.2f입니다.", rate);
    }


    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottoList()) {
            System.out.println(lotto);
        }
    }
}
