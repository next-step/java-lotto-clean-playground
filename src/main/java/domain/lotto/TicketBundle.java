package domain.lotto;

import domain.lotto.wrappers.CorrectCount;
import domain.lotto.wrappers.Result;
import domain.lotto.wrappers.TicketCount;
import number_generator.wrappers.Count;
import number_generator.NumberListGenerator;

import java.util.ArrayList;
import java.util.List;

public class TicketBundle {
    private final List<Ticket> ticketBundle;

    public TicketBundle() {
        this.ticketBundle = new ArrayList<>();
    }

    public List<Ticket> getTicketBundle() {
        return new  ArrayList<>(ticketBundle);
    }

    public void createRandomTickets(TicketCount ticketCount, NumberListGenerator numberListGenerator) {
        Count count = new Count(Ticket.TICKET_LENGTH);

        for (int i = 0; i < ticketCount.getValue(); i++) {
            ticketBundle.add(new Ticket(numberListGenerator.generateDistinctSortedNumbers(count, Number.LOWER_BOUND, Number.UPPER_BOUND).stream().map(Number::new).toList()));
        }
    }

    public Result createResult(WinnerBalls winnerBalls) {
        List<CorrectCount> correctCounts = new ArrayList<>();

        for (Ticket ticket : ticketBundle) {
            correctCounts.add(ticket.createCorrectCount(winnerBalls));
        }

        return new Result(correctCounts);
    }
}
