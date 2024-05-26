package view;

import domain.LottoTicketDto;
import domain.Ranking;
import java.util.List;

public class OutputView {

    private static final String LINE = "-----------";

    public void displayLottoTickets(List<LottoTicketDto> tickets) {
        for (LottoTicketDto ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void NumberOfWinning(Ranking ranking) {
        if (ranking != Ranking.ERROR) {
            System.out.printf("%s (%,d원)\n", ranking.getMessage(), ranking.getPrice());
        }
    }

    public void LottoRateOfResult(double rateOfWinning) {
        System.out.println(LINE);
        System.out.printf("총 수익률은 %.2f입니다.\n", rateOfWinning);
    }
}
