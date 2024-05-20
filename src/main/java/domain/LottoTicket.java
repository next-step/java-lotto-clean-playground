package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<LottoTicketStore> lottoTicket;
    private static final int INITIAL_NUMBER = 0;
    private static final int TICKET_PRICE = 1000;
    private final int ticketCount;
    private final NumberGenerator numberGenerator;

    public List<LottoTicketStore> getLottoTicket() {
        return new ArrayList<>(lottoTicket);
    }

    private LottoTicket(int money, NumberGenerator numberGenerator) {
        this.ticketCount = money / TICKET_PRICE;
        this.numberGenerator = numberGenerator;
        this.lottoTicket = generateLottoTickets();
    }

    private List<LottoTicketStore> generateLottoTickets() {
        List<LottoTicketStore> tickets = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < ticketCount; i++) {
            tickets.add(new LottoTicketStore(numberGenerator));
        }
        return tickets;
    }
}
