package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final int THREE_CORRECT = 3;
    public static final int FOUR_CORRECT = 4;
    public static final int FIVE_CORRECT = 5;
    public static final int SIX_CORRECT = 6;

    private final List<LottoTicket> tickets;

    public Lotto(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public LottoResult getResults(LottoTicket winnerTicket) {
        List<Integer> results = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            results.add(ticket.getCorrectCount(winnerTicket));
        }
        return new LottoResult(
                Collections.frequency(results, THREE_CORRECT),
                Collections.frequency(results, FOUR_CORRECT),
                Collections.frequency(results, FIVE_CORRECT),
                Collections.frequency(results, SIX_CORRECT)
        );
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public int getNumberOfTickets() {
        return tickets.size();
    }
}
