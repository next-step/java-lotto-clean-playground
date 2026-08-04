package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultView {

    public static void printLottoNumberCount(int manualLottoCount, int autoLottoCount) {
        System.out.println("\n수동으로 " + manualLottoCount + "장, "
                + "자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    public static void printLotto(Lottos lottos) {
        List<String> lottoForms = getLottoForms(lottos.getLottos());
        for(int i = 0; i < lottos.getLottoNumberCount(); i++){
            System.out.println(lottoForms.get(i));
        }
        System.out.println();
    }


    public static void printWinningStatistics(Map<Rank, Integer> correctCount, float profit) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println(getWinningForm(Rank.FIFTH, correctCount));
        System.out.println(getWinningForm(Rank.FOURTH, correctCount));
        System.out.println(getWinningForm(Rank.THIRD, correctCount));
        System.out.println(getWinningForm(Rank.SECOND, correctCount));
        System.out.println(getWinningForm(Rank.FIRST, correctCount));
        System.out.println("총 수익률은 " + profit + "입니다.");
    }


    private static String getWinningForm(Rank rank, Map<Rank, Integer> correctCount) {
        if(rank == Rank.SECOND) {
            return rank.getMatchCount()
                    + "개 일치, 보너스 볼 일치 (" + rank.getPrize() + "원) - "
                    + correctCount.get(rank) + "개";
        }
        return rank.getMatchCount()
                + "개 일치 (" + rank.getPrize() + "원) - "
                + correctCount.get(rank) + "개";
    }

    private static String getLottoForm(List<LottoNumber> lotto){ // 로또 출력 폼 반환
        StringBuilder form = new StringBuilder("[");
        for (int i = 0; i < lotto.size() - 1; i++){
            form.append(lotto.get(i)).append(", ");
        }
        form.append(lotto.get(lotto.size() - 1) + "]");
        return form.toString();
    }

    private static List<String> getLottoForms(List<Lotto> lottos) { // 로또 출력 폼 리스트 반환
        List<String> lottoForms = new ArrayList<>();
        for(int i = 0; i < lottos.size(); i++){
            lottoForms.add(getLottoForm(lottos.get(i).getLotto()));
        }
        return lottoForms;
    }
}
