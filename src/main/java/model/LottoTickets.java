package model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<Lotto> tickets;

    private LottoTickets(List<Lotto> tickets, boolean unused) {
        this.tickets = List.copyOf(tickets);
    }

    public LottoTickets(List<List<Integer>> manualNumbers) {
        List<Lotto> manualTickets = new ArrayList<>();
        addManualTickets(manualTickets, manualNumbers);
        this.tickets = List.copyOf(manualTickets);
    }

    public LottoTickets(int autoTicketCount) {
        List<Lotto> autoTickets = new ArrayList<>();
        addAutoTickets(autoTickets, autoTicketCount);
        this.tickets = List.copyOf(autoTickets);
    }

    public static LottoTickets merge(LottoTickets manualTickets, LottoTickets autoTickets) {
        List<Lotto> mergedTickets = new ArrayList<>(manualTickets.tickets);
        mergedTickets.addAll(autoTickets.tickets);
        return new LottoTickets(mergedTickets, true);
    }

    private static void addManualTickets(List<Lotto> tickets, List<List<Integer>> manualNumbers) {
        for (List<Integer> numbers : manualNumbers) {
            tickets.add(new Lotto(numbers));
        }
    }

    private static void addAutoTickets(List<Lotto> tickets, int autoTicketCount) {
        for (int i = 0; i < autoTicketCount; i++) {
            tickets.add(new Lotto(new LottoNumbers()));
        }
    }

    public List<Lotto> getTickets() {
        return tickets;
    }

    public List<List<Integer>> getFormattedTicketNumbers() {
        List<List<Integer>> ticketNumbers = new ArrayList<>();
        for (Lotto ticket : tickets) {
            ticketNumbers.add(ticket.getNumbers());
        }
        return ticketNumbers;
    }
}
