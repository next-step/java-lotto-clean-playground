package model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> tickets;

    public LottoTickets(List<List<Integer>> manualNumbers, int autoTicketCount) {
        this.tickets = new ArrayList<>();
        addManualTickets(manualNumbers);
        addAutoTickets(autoTicketCount);
    }

    private void addManualTickets(List<List<Integer>> manualNumbers) {
        for (List<Integer> numbers : manualNumbers) {
            tickets.add(new Lotto(numbers));
        }
    }

    private void addAutoTickets(int autoTicketCount) {
        for (int i = 0; i < autoTicketCount; i++) {
            tickets.add(new Lotto());
        }
    }

    public List<Lotto> getTickets() {
        return new ArrayList<>(tickets);
    }
}
