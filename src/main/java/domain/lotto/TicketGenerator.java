package domain.lotto;

import domain.lotto.exception.TicketSizeMismatchException;
import domain.lotto.wrappers.TicketCount;
import number_generator.NumberListGenerator;
import number_generator.wrappers.Count;

import java.util.ArrayList;
import java.util.List;

public class TicketGenerator {
    private final TicketCount manualTicketCount;

    private final TicketCount randomTicketCount;

    public TicketGenerator(TicketCount manualTicketCount, TicketCount randomTicketCount) {
        this.manualTicketCount = manualTicketCount;
        this.randomTicketCount = randomTicketCount;
    }

    public List<Ticket> createRandomTickets(NumberListGenerator numberListGenerator) {
        Count count = new Count(Ticket.TICKET_LENGTH);
        List<Ticket> randomTickets = new ArrayList<>();

        for (int i = 0; i < randomTicketCount.getValue(); i++) {
            randomTickets.add(new Ticket(numberListGenerator.generateDistinctSortedNumbers(count, Ball.LOWER_BOUND, Ball.UPPER_BOUND).stream().map(Ball::new).toList()));
        }

        return randomTickets;
    }

    public List<Ticket> createManualTickets(List<Ticket> manualTickets) {
        if (manualTickets.size() != manualTicketCount.getValue()) {
            throw new TicketSizeMismatchException("You made wrong number of tickets.");
        }

        return manualTickets;
    }

    public TicketCount getManualTicketCount() {
        return new TicketCount(manualTicketCount.getValue());
    }

    public TicketCount getRandomTicketCount() {
        return new TicketCount(randomTicketCount.getValue());
    }
}
