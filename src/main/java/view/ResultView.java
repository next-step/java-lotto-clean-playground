package view;

import domain.Lottos;
import java.util.List;

public class ResultView {

    public static void printLottoNumberCount(int lottoNumberCount) {
        System.out.println("\n" + lottoNumberCount + "개를 구매했습니다.");
    }

    public static void printLotto(Lottos lottos) {
        List<String> lottoForms = lottos.getLottoForms();
        for(int i = 0; i < lottos.getLottoNumberCount(); i++){
            System.out.println(lottoForms.get(i));
        }
        System.out.println();
    }

    public static void printWinningStatistics(List<Integer> correctCount, float profit) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println("3개 일치 (5000원) - " + correctCount.get(0) + "개");
        System.out.println("4개 일치 (50000원) - " + correctCount.get(1) + "개");
        System.out.println("5개 일치 (1500000원) - " + correctCount.get(2) + "개");
        System.out.println("6개 일치 (2000000000원) - " + correctCount.get(3) + "개");
        System.out.println("총 수익률은 " + profit + "입니다.");
    }

}
