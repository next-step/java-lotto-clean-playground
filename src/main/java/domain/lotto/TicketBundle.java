package domain.lotto;

import domain.lotto.wrappers.CorrectCount;
import domain.lotto.wrappers.Result;

import java.util.ArrayList;
import java.util.List;

public class TicketBundle {
    private final List<Ticket> tickets;

    public TicketBundle() {
        this.tickets = new ArrayList<>();
    }

    public List<Ticket> getTickets() {
        return new  ArrayList<>(tickets);
    }

    public void addTickets(List<Ticket> tickets) {
        this.tickets.addAll(tickets);
    }

    public Result createResult(WinnerBalls winnerBalls) {
        List<CorrectCount> correctCounts = new ArrayList<>();

        for (Ticket ticket : tickets) {
            correctCounts.add(ticket.createCorrectCount(winnerBalls));
        }

        return new Result(correctCounts);
    }
}
