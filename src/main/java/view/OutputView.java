package view;

import domain.LottoTicket;

import java.util.List;

public class OutputView {

    private static final int THREE_COUNT = 3;
    private static final int FOUR_COUNT = 4;
    private static final int FIVE_COUNT = 5;
    private static final int SIX_COUNT = 6;
    private static final int INITIAL_NUMBER_ZERO = 0;
    private static final int INITIAL_NUMBER_ONE = 1;
    private static final int INITIAL_NUMBER_TWO = 2;
    private static final int INITIAL_NUMBER_THREE = 3;


    public void displayLottoTickets(List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void numberOfWinning(int count, List<Integer> matchesMoney) {
        if (count == THREE_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, matchesMoney.get(INITIAL_NUMBER_ZERO));
        } else if (count == FOUR_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, matchesMoney.get(INITIAL_NUMBER_ONE));
        } else if (count == FIVE_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, matchesMoney.get(INITIAL_NUMBER_TWO));
        } else if (count == SIX_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, matchesMoney.get(INITIAL_NUMBER_THREE));
        } else {
            System.out.printf("%d개 일치\n", count);
        }
    }

    public void winningLottoRateOfResult(double rateOfWinning) {
        System.out.printf("총수익률은 %.2f", rateOfWinning);
    }
}