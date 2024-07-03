package view;

import domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printLottoTickets(int AutoLottoTickets, int PassiveLottoTickets) {
        System.out.println("자동" + AutoLottoTickets + "개, 수동" + PassiveLottoTickets + "개 를 구매했습니다.");
    }

    public void printLotto(List<Integer> LottoList) {
        System.out.println(LottoList);
    }

    public void printWinningResult(Map<LottoRank, Integer> rankCounts) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) {
                continue;
            }
            System.out.println(rank.getCount() + "개 일치 (" + (rank.isBonusMatch() ? "보너스 볼 일치" : "") + ") " + rank.getPrize() + "원 - " + rankCounts.getOrDefault(rank, 0) + "개");
        }
    }

    public void printProfit(double profit) {
        System.out.println("총 수익률은 " + profit + "입니다.");
    }
}
