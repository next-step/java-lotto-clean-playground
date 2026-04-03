package domain;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private final LottoTicketGenerator lottoTicketGenerator;

    public Cashier(LottoTicketGenerator lottoTicketGenerator) {
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public Lotto generateTickets(Price price) {
        int numberOfTickets = price.getBuyableLottoCount();
        List<LottoTicket> generatedTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            generatedTickets.add(lottoTicketGenerator.generate());
        }
        return new Lotto(generatedTickets);
    }

    public double getProfitRate(LottoResult result, Price price) {
        int totalProfit = 5000 * result.threeCorrectCount()
                + 50000 * result.fourCorrectCount()
                + 1500000 * result.fiveCorrectCount()
                + 2000000000 * result.sixCorrectCount();

        return price.calculateProfitRate(totalProfit);
    }
}
