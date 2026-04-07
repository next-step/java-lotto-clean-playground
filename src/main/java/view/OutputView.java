package view;

import domain.lotto.Number;
import domain.lotto.Ticket;
import domain.lotto.TicketBundle;
import domain.lotto.wrappers.Result;
import domain.lotto.wrappers.ProfitRate;
import domain.lotto.wrappers.TicketCount;

import java.util.List;

import static domain.lotto.Rank.*;

public class OutputView {
    public void showLottoTickets(TicketBundle lotto) {
        System.out.println();
        System.out.println(lotto.getTicketBundle().size() + "개를 구매했습니다.");
        for (Ticket ticket : lotto.getTicketBundle()) {
            showLottoTicket(ticket);
        }
    }

    public void showLottoResults(TicketCount ticketCount, Result result) {
        showLottoStatistics(result);
        showProfitRate(ticketCount, result);
    }

    public void showLottoStatistics(Result result) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println("3개 일치 (" + THREE_CORRECT.getPrizeMoney() + "원)- " + result.getThreeCorrectCount() + "개");
        System.out.println("4개 일치 (" + FOUR_CORRECT.getPrizeMoney() + "원)- " + result.getFourCorrectCount() + "개");
        System.out.println("5개 일치 (" + FIVE_CORRECT.getPrizeMoney() + "원)- " + result.getFiveCorrectCount() + "개");
        System.out.println("5개 + 보너스볼 일치 (" + FIVE_AND_BONUS_CORRECT.getPrizeMoney() + "원)- " + result.getFiveAndBonusCorrectCount() + "개");
        System.out.println("6개 일치 (" + SIX_CORRECT.getPrizeMoney() + "원)- " + result.getSixCorrectCount() + "개");
    }

    public void showProfitRate(TicketCount ticketCount, Result result) {
        ProfitRate profitRate = result.calculateProfitRate(ticketCount);

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

    private void showLottoTicket(Ticket lottoTicket) {
        List<Integer> ticket = lottoTicket.getTicket().stream().map(Number::getNumber).toList();
        System.out.println(ticket);
    }
}
