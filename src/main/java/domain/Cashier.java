package domain;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private final LottoTicketGenerator lottoTicketGenerator;

    public Cashier(LottoTicketGenerator lottoTicketGenerator) {
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public Lotto generateTickets(Price price) {
        int ticketCount = price.getBuyableLottoCount();
        List<LottoTicket> generatedTickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            generatedTickets.add(lottoTicketGenerator.generate());
        }
        return new Lotto(generatedTickets);
    }

    public double getProfitRate(LottoResult result, Price price) {
        int totalProfit = 0;
        for (LottoRank lottoRank : LottoRank.values()) {
            totalProfit += lottoRank.getPrizeMoney() * result.getMatchCount(lottoRank);
        }
        return price.calculateProfitRate(totalProfit);
    }
}
