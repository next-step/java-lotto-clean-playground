package view;

import java.util.List;

public class OutputView {

    public void printLottoTickets(int LottoTickets) {
        System.out.println(LottoTickets + "개를 구매했습니다.");
    }

    public void printLotto(List<Integer> LottoList) {
        System.out.println(LottoList);
    }

    public void printWinningResult(int[] rankCounts, List<int[]> Ranks){
        System.out.println("당첨 통계");
        System.out.println("--------");
        for (int i=0;i<rankCounts.length;i++){
            int[] rank=Ranks.get(i);
            System.out.println(rank[0]+"개 일치 "+ rank[1]+"원- "+rankCounts[i]);
        }
    }

    public void printProfit(double profit){
        System.out.println("총 수익률은 "+profit+"입니다.");
    }


}
