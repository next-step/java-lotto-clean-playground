package view;

import domain.lotto.Lotto;
import domain.lottoTicket.LottoTicket;
import java.util.List;

public class OutputView {
    private final String RIGHT_THREE_NUMBERS = "3개 일치 (5000원)- %d개\n";
    private final String RIGHT_FOUR_NUMBERS = "4개 일치 (50000원)- %d개\n";
    private final String RIGHT_FIVE_NUMBERS = "5개 일치 (1500000원)- %d개\n";
    private final String RIGHT_SIX_NUMBERS = "6개 일치 (2000000000원)- %d개\n";
    private final String EARNING_RATE = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";
    public void outputLottoTickets(Lotto lotto, List<LottoTicket> lottoTickets) {
        System.out.println("\n" + lotto.getLottoTicketCount() + "개를 구매했습니다.");

        lottoTickets.forEach(lottoTicket -> System.out.println(lottoTicket.getLottoNumber()));

    }

    public void outputLottoWinningStatistics(List<Integer> winningStatistics) {
        System.out.println("\n" + "당첨 통계");

        System.out.printf(RIGHT_THREE_NUMBERS, (int) winningStatistics.stream().filter(count -> count == 3).count());
        System.out.printf(RIGHT_FOUR_NUMBERS, (int) winningStatistics.stream().filter(count -> count == 4).count());
        System.out.printf(RIGHT_FIVE_NUMBERS, (int) winningStatistics.stream().filter(count -> count == 5).count());
        System.out.printf(RIGHT_SIX_NUMBERS, (int) winningStatistics.stream().filter(count -> count == 6).count());
        System.out.printf(EARNING_RATE, 2.34);
    }
}
