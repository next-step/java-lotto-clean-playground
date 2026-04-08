package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<LottoTicket> tickets;

    public Lotto(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public LottoResult getResults(LottoTicket winnerTicket, LottoNumber bonusNumber) {
        List<LottoRank> lottoRankOfEachTicket = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            lottoRankOfEachTicket.add(ticket.getLottoRank(winnerTicket, bonusNumber));
        }
        List<Integer> matchingTicketCounts = new ArrayList<>();
        for (LottoRank lottoRank: LottoRank.values()) {
            matchingTicketCounts.add(Collections.frequency(lottoRankOfEachTicket, lottoRank));
        }
        return new LottoResult(matchingTicketCounts);
    }

    public int getNumberOfTickets() {
        return tickets.size();
    }

    @Override
    public String toString() {
        return String.join("\n", tickets.stream().map(LottoTicket::toString).toList());
    }
}
