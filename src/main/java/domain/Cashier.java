package domain;

import java.util.ArrayList;
import java.util.List;

public class Cashier {
    private final LottoTicketGenerator lottoTicketGenerator;

    public Cashier(LottoTicketGenerator lottoTicketGenerator) {
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public Lotto generateTickets(int price) {
        validatePrice(price);
        int numberOfTickets = calculateNumberOfTickets(price);
        List<LottoTicket> generatedTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            generatedTickets.add(lottoTicketGenerator.generate());
        }
        return new Lotto(generatedTickets);
    }

    public Double getProfitRate(LottoResult result, int price) {
        int totalProfit = 5000 * result.threeCorrectCount()
                + 50000 * result.fourCorrectCount()
                + 1500000 * result.fiveCorrectCount()
                + 2000000000 * result.sixCorrectCount();

        return (double) totalProfit / price;
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        if (price < 1000) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("1000원 단위로 입력해주세요.");
        }
    }

    private int calculateNumberOfTickets(int price) {
        return price / 1000;
    }
}
