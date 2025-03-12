package model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> tickets;
    public static final int LOTTO_PRICE = 1000;

    public LottoTickets(int ticketCount) {
        this.tickets = generateLottoTickets(ticketCount);
    }

    private List<Lotto> generateLottoTickets(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Lotto());
        }
        return tickets;
    }

    public List<Lotto> getTickets() {
        return new ArrayList<>(tickets);
    }

    public static int getTicketCount(int purchaseAmount){
        return purchaseAmount / LOTTO_PRICE;
    }
}
