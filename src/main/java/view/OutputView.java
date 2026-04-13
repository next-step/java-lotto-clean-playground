package view;

import domain.lotto.*;

import java.util.List;

import static domain.lotto.Rank.*;

public class OutputView {
    public void showGeneratedTickets(TicketGenerator ticketGenerator, TicketBundle ticketBundle) {
        System.out.println();

        int manualTicketCount = ticketGenerator.getManualTicketCount().getValue();
        int randomTicketCount = ticketGenerator.getRandomTicketCount().getValue();

        System.out.println("수동으로 " + manualTicketCount + "장, 자동으로 " + randomTicketCount + "개를 구매했습니다.");

        for (Ticket ticket : ticketBundle.getTickets()) {
            showTicket(ticket);
        }
    }

    private void showTicket(Ticket lottoTicket) {
        List<Integer> ticket = lottoTicket.getBalls().stream().map(Ball::getNumber).toList();
        System.out.println(ticket);
    }

    public void showResults(Payment payment, Result result) {
        showStatistics(result);
        showProfitRate(payment, result);
    }

    public void showStatistics(Result result) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println("3개 일치 (" + THREE_CORRECT.getPrizeMoney() + "원)- " + result.getCount(THREE_CORRECT) + "개");
        System.out.println("4개 일치 (" + FOUR_CORRECT.getPrizeMoney() + "원)- " + result.getCount(FOUR_CORRECT) + "개");
        System.out.println("5개 일치 (" + FIVE_CORRECT.getPrizeMoney() + "원)- " + result.getCount(FIVE_CORRECT) + "개");
        System.out.println("5개, 보너스볼 일치 (" + FIVE_AND_BONUS_CORRECT.getPrizeMoney() + "원)- " + result.getCount(FIVE_AND_BONUS_CORRECT) + "개");
        System.out.println("6개 일치 (" + SIX_CORRECT.getPrizeMoney() + "원)- " + result.getCount(SIX_CORRECT) + "개");
    }

    public void showProfitRate(Payment payment, Result result) {
        ProfitRate profitRate = result.calculateProfitRate(payment);

        System.out.printf("총 수익률은 %.2f입니다.", profitRate.getValue());
        if (profitRate.getValue() > 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");
            return;
        }

        if (profitRate.getValue() == 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 본전이라는 의미임)");
            return;
        }

        System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}
