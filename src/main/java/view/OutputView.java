package view;

import domain.LottoTicket;

import domain.WinningPrizeDto;
import domain.WinningResultDto;
import java.util.List;

public class OutputView {

    public void printLottoList(int count, List<LottoTicket> lottoTickets){
        System.out.println( count + "개를 구매했습니다.");

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.toDisplayString());
        }
    }

    public void printResult(WinningResultDto winningResult, double revenue) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (" + WinningPrizeDto.THREE_MATCH_PRIZE + "원)- "+ winningResult.getThreeMatchCount() +"개");
        System.out.println("4개 일치 (" + WinningPrizeDto.FOUR_MATCH_PRIZE + "원)- "+ winningResult.getFourMatchCount() +"개");
        System.out.println("5개 일치 (" + WinningPrizeDto.FIVE_MATCH_PRIZE + "원)- "+ winningResult.getFiveMatchCount() +"개");
        System.out.println("6개 일치 (" + WinningPrizeDto.SIX_MATCH_PRIZE + "원)- "+ winningResult.getSixMatchCount() +"개");
        System.out.println("총 수익률은 "+ revenue +"입니다.");

    }
}
