package view;

import java.util.ArrayList;
import java.util.HashMap;
import domain.WinningRank;
import vo.Lotto;

public class LottoOutputView {

    // 구매한 로또 번호들을 출력하는 메서드
    public void printLottoNumbers(ArrayList<Lotto> buyLotto, int manualLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + (buyLotto.size()-manualLottoCount) + "개를 구매했습니다.");

        for (Lotto lotto : buyLotto) {
            System.out.println(lotto);
        }
    }

    // 당첨 통계와 수익률을 출력하는 메서드
    public void printWinningStatistics(HashMap<WinningRank, Integer> winningStatistics, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printEachRankStatistics(winningStatistics);
        printProfitRate(profitRate);
    }

    private void printEachRankStatistics(HashMap<WinningRank, Integer> winningStatistics) {
        System.out.println("3개 일치 (5000원)- " + winningStatistics.get(WinningRank.FIFTH) + "개");
        System.out.println("4개 일치 (50000원)- " + winningStatistics.get(WinningRank.FOURTH) + "개");
        System.out.println("5개 일치 (1500000원)- " + winningStatistics.get(WinningRank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + winningStatistics.get(WinningRank.SECOND) + "개");
        System.out.println("6개 일치 (2000000000원)- " + winningStatistics.get(WinningRank.FIRST) + "개");
    }

    private void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "입니다.");
    }

}
