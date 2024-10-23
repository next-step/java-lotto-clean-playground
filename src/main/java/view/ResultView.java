package view;

import model.*;

import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    public void printLottoTicketsCountSentence(int manualLottoTicketCount, int automaticLottoTicketCount){
        System.out.printf("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.\n",manualLottoTicketCount,automaticLottoTicketCount);
    }

    public void printLottoTicketsInformation(LottoTickets lottoTickets) {

        StringBuilder sb = new StringBuilder();

        for (LottoTicket ticket : lottoTickets.getTickets()) {

            String result = ticket.getNumbers()
                    .stream()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                    .collect(Collectors.joining(","));

            sb.append("[").append(result).append("]\n");
        }

        System.out.println(sb);

    }

    public void printLottoWinningStatistics(LottoResult lottoResult){

        Map<LottoRank,Integer> resultMap = lottoResult.getWinningResult();

        System.out.println("당첨 통계\n---------");

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NONE) continue;;

            System.out.printf("%d개 일치",rank.getMatchCount());

            printPhraseIfNumberMatchesBonusBall(rank);

            System.out.printf(" (%d원) - %d개\n", rank.getPrize(),resultMap.get(rank));
        }

    }

    private static void printPhraseIfNumberMatchesBonusBall(LottoRank rank) {
        if (rank == LottoRank.SECOND && rank.isMatchBonusBall()){
            System.out.printf(", 보너스 볼 일치");
        }
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.\n", earningRate);
    }
}
