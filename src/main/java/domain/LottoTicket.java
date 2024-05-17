package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<CreateLottoTicket> lottoTicket;

    private static final int TICKET_PRICE = 1000;
    private final int ticketCount;

    public LottoTicket(int money) {
        this.ticketCount = money / TICKET_PRICE;
        this.lottoTicket = generateLottoTickets();
    }

    public List<CreateLottoTicket> getLottoTicket() {
        return new ArrayList<>(lottoTicket);
    }

    private List<CreateLottoTicket> generateLottoTickets() {
        List<CreateLottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new CreateLottoTicket());
        }
        return tickets;
    }
}
