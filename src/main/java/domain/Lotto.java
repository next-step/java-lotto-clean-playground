package domain;

import domain.wrappers.LottoResult;
import domain.wrappers.TicketCount;
import number_generator.wrappers.NumberCount;
import number_generator.NumberListGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int TICKET_LENGTH = 6;

    private final List<LottoTicket> lottoTickets;

    public Lotto() {
        this.lottoTickets = new ArrayList<>();
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public void createRandomTickets(TicketCount ticketCount, NumberListGenerator numberListGenerator) {
        for (int i = 0; i < ticketCount.getValue(); i++) {
            lottoTickets.add(new LottoTicket(numberListGenerator.generate(new NumberCount(TICKET_LENGTH))));
        }
    }

    public LottoResult createLottoResult(LottoTicket winnerTicket) {
        List<Integer> correctCounts = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            correctCounts.add(lottoTicket.calculateCorrectCount(winnerTicket).getValue());
        }

        return new LottoResult(correctCounts);
    }
}
