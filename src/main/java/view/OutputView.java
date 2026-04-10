package view;

import model.LottoRank;
import model.LottoTicket;
import model.LottoTickets;

import java.util.Map;

import static model.LottoRank.MISS;

public class OutputView {
    public void printPurchaseSummary(int manualQuantity, int autoQuantity) {
        System.out.println("\n" + "수동으로 " + manualQuantity + "장, 자동으로 " + autoQuantity + "개를 구매했습니다.");

    }

    public void printMytickets(LottoTickets tickets) {

        for (LottoTicket ticket : tickets.getTickets()) {
            System.out.println(ticket.getLottoNumbers());
        }
        System.out.println(" ");
    }


    public void printHead() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printStatus(Map<LottoRank, Integer> lottoStatus) {
        for (LottoRank lottoRank : LottoRank.values()) {
            int count = lottoStatus.get(lottoRank);
            if (lottoRank == MISS) {
                break;
            }
            System.out.println(lottoRank.getMatchCount() + "개 일치 ("
                    + lottoRank.getWinningMoney() + "원)- "
                    + count + "개");
        }
    }

    public void printProfit(double ror) {
        System.out.printf("총 수익률은 %.2f입니다.", ror);
    }
}
