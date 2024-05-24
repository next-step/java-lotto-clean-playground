package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private static final int INITIAL_NUMBER = 0;
    private final NumberGenerator numberGenerator;

    public LottoTickets(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<LottoTicket> generateLottoTicket(int ticketCount) {
        List<LottoTicket> tickets = new ArrayList<>();
        generateLottoTickets(ticketCount, tickets);
        return tickets;
    }

    private void generateLottoTickets(int ticketCount, List<LottoTicket> tickets) {
        for (int i = INITIAL_NUMBER; i < ticketCount; i++) {
            List<Integer> numbers = numberGenerator.generateRandomNumber();
            LottoTicket ticket = new LottoTicket(numbers);
            tickets.add(ticket);
        }
    }
}