package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private static final int INITIAL_NUMBER = 0;
    private final NumberGenerator numberGenerator;

    public LottoTickets(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<LottoTicket> generateLottoTickets(int ticketCount) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < ticketCount; i++) {
            List<Integer> numbers = numberGenerator.generateRandomNumber();
            LottoTicket ticket = new LottoTicket(numbers);
            tickets.add(ticket);
        }
        return tickets;
    }
}