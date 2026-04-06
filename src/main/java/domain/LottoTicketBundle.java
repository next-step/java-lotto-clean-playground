package domain;

import domain.wrappers.CorrectCount;
import domain.wrappers.LottoResult;
import domain.wrappers.TicketCount;
import number_generator.LottoNumberListGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketBundle {
    private final List<LottoTicket> ticketBundle;

    public LottoTicketBundle() {
        this.ticketBundle = new ArrayList<>();
    }

    public List<LottoTicket> getTicketBundle() {
        return new  ArrayList<>(ticketBundle);
    }

    public void createRandomTickets(TicketCount ticketCount, LottoNumberListGenerator lottoNumberListGenerator) {
        for (int i = 0; i < ticketCount.getValue(); i++) {
            ticketBundle.add(new LottoTicket(lottoNumberListGenerator.generate().stream().map(LottoNumber::new).toList()));
        }
    }

    public LottoResult createLottoResult(LottoTicket winnerTicket) {
        List<CorrectCount> correctCounts = new ArrayList<>();

        for (LottoTicket lottoTicket : ticketBundle) {
            correctCounts.add(new CorrectCount(lottoTicket.calculateCorrectCount(winnerTicket).getValue()));
        }

        return new LottoResult(correctCounts);
    }
}
