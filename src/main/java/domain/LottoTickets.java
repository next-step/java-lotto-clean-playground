package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private static final int INITIAL_NUMBER = 0;

    private final NumberGenerator numberGenerator;
    private final List<LottoTicket> tickets;

    public LottoTickets(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.tickets = new ArrayList<>();
    }

    public void addPassiveTickets(List<List<Integer>> passiveNumbers) {
        for (List<Integer> numbers : passiveNumbers) {
            tickets.add(new LottoTicket(numbers));
        }
    }

    public List<LottoTicket> generateLottoTickets(int ticketCount) {
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
