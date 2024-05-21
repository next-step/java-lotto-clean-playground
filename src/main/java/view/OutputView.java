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
    private int sum = 0;

    private static final List<Integer> winningsMoney = List.of(5000, 50000, 1500000, 20000000);

    public void displayLottoTickets(List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private static List<Integer> winningsMoney() {
        return winningsMoney;
    }

    public void numberOfWinning(int count) {
        if (count == THREE_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, winningsMoney.get(INITIAL_NUMBER_ZERO));
        } else if (count == FOUR_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, winningsMoney.get(INITIAL_NUMBER_ONE));
        } else if (count == FIVE_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, winningsMoney.get(INITIAL_NUMBER_TWO));
        } else if (count == SIX_COUNT) {
            System.out.printf("%d개 일치 (%d원)\n", count, winningsMoney.get(INITIAL_NUMBER_THREE));
        } else {
            System.out.printf("%d개 일치\n", count);
        }
    }

    public void winningLottoRateOfResult(int payMoney) {
        for (int i = 0; i < winningsMoney().size(); i++)
            sum += winningsMoney().get(i);
        double rateOfWinning = (double) sum / payMoney;
        System.out.printf("총수익률은 %.2f", rateOfWinning);
    }
}
