package domain;

import java.util.List;

public class Cashier {
    private final LottoTicketGenerator lottoTicketGenerator;

    public Cashier(LottoTicketGenerator lottoTicketGenerator) {
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public Lotto generateTickets(Price price, List<LottoTicket> manualTickets) {
        int ticketCount = price.getBuyableLottoCount() - manualTickets.size();
        for (int i = 0; i < ticketCount; i++) {
            manualTickets.add(lottoTicketGenerator.generate());
        }
        return new Lotto(manualTickets);
    }

    public double getProfitRate(LottoResult result, Price price) {
        int totalProfit = 0;
        for (LottoRank lottoRank : LottoRank.values()) {
            totalProfit += lottoRank.getPrizeMoney() * result.getMatchCount(lottoRank);
        }
        return price.calculateProfitRate(totalProfit);
    }
}
