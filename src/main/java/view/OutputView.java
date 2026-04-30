package view;

import domain.LottoTicket;
import domain.WinningRank;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printLottoList(int manualCount, int allCount ,List<LottoTicket> lottoTickets){
        System.out.println( "수동으로 " + manualCount + " 장, 자동으로 " + (allCount - manualCount) + " 개를 구매했습니다.");

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.toDisplayString());
        }
    }

    public void printResult(Map<WinningRank, Integer> result, double revenue) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (WinningRank rank : WinningRank.values()) {
            if (rank == WinningRank.FIVE_BONUS) {
                System.out.println(rank.getMatchCount() + "개 일치, 보너스 볼 일치(" + rank.getPrize() + "원) - " + result.get(rank) + "개");
                continue;
            }
            System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원) - " + result.get(rank) + "개");
        }

        System.out.printf("총 수익률은 %.2f 입니다.%n", revenue);
    }
}
