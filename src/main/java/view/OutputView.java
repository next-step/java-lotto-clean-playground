package view;

import java.util.List;

public class OutputView {

    public void printLottoTickets(int lottoTickets) {
        System.out.println(lottoTickets + "개를 구매했습니다.");
    }

    public void printLotto(List<Integer> LottoList) {
        System.out.println(LottoList);
    }

    public void printWinningResult(List<Integer> rankCounts, List<int[]> ranks) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        for (int i = 0; i < rankCounts.size(); i++) {
            int[] rank = ranks.get(i);
            System.out.println(rank[0] + "개 일치 " + rank[1] + "원- " + rankCounts.get(i));
        }
    }


    public void printProfit(double profit) {
        System.out.println("총 수익률은 " + profit + "입니다.");
    }

}
