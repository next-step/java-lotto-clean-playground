package view;

import domain.Lottos;
import domain.Rank;
import java.util.List;
import java.util.Map;

public class ResultView {

    public static void printLottoNumberCount(int manualLottoCount, int autoLottoCount) {
        System.out.println("\n수동으로 " + manualLottoCount + "장, "
                + "자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    public static void printLotto(Lottos lottos) {
        List<String> lottoForms = lottos.getLottoForms();
        for(int i = 0; i < lottos.getLottoNumberCount(); i++){
            System.out.println(lottoForms.get(i));
        }
        System.out.println();
    }


    public static void printWinningStatistics(Map<Rank, Integer> correctCount, float profit) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println(WinningForm(Rank.FIFTH, correctCount));
        System.out.println(WinningForm(Rank.FOURTH, correctCount));
        System.out.println(WinningForm(Rank.THIRD, correctCount));
        System.out.println(WinningForm(Rank.SECOND, correctCount));
        System.out.println(WinningForm(Rank.FIRST, correctCount));
        System.out.println("총 수익률은 " + profit + "입니다.");
    }


    private static String WinningForm(Rank rank, Map<Rank, Integer> correctCount) {
        if(rank == Rank.SECOND) {
            return rank.getMatchCount()
                    + "개 일치, 보너스 볼 일치 (" + rank.getPrize() + "원) - "
                    + correctCount.get(rank) + "개";
        }
        return rank.getMatchCount()
                + "개 일치 (" + rank.getPrize() + "원) - "
                + correctCount.get(rank) + "개";
    }

}
