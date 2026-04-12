package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<LottoTicket> tickets;

    public Lotto(Price price, LottoTicketGenerator lottoTicketGenerator, List<LottoTicket> manualTickets) {
        int ticketCount = price.getBuyableLottoCount() - manualTickets.size();
        for (int i = 0; i < ticketCount; i++) {
            manualTickets.add(lottoTicketGenerator.generate());
        }
        this.tickets = manualTickets;
    }

    public LottoResult getResults(LottoTicket winnerTicket, LottoNumber bonusNumber) {
        List<LottoRank> lottoRankOfEachTicket = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            lottoRankOfEachTicket.add(ticket.getLottoRank(winnerTicket, bonusNumber));
        }
        return new LottoResult(lottoRankOfEachTicket);
    }

    public int getNumberOfTickets() {
        return tickets.size();
    }

    @Override
    public String toString() {
        return String.join("\n", tickets.stream().map(LottoTicket::toString).toList());
    }
}
