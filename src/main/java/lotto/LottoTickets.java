package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = Collections.unmodifiableList(new ArrayList<>(tickets));
    }

    public int size() {
        return tickets.size();
    }

    public List<LottoTicket> asList() {
        return tickets;
    }

    public int countTicketsWithMatchCount(int matchCount, LottoTicket winningTicket) {
        int count = 0;
        for (LottoTicket ticket : tickets) {
            if (ticket.countMatches(winningTicket) == matchCount) {
                count++;
            }
        }
        return count;
    }

    public Money sumOfPrizes(WinningNumbers winningNumbers) {
        int total = 0;
        for (LottoTicket ticket : tickets) {
            int matches = ticket.countMatches(winningNumbers.getWinningTicket());
            boolean bonus = ticket.contains(winningNumbers.getBonusNumber());
            Rank rank = Rank.from(matches, bonus);
            if (rank != null) {
                total += rank.getPrize();
            }
        }
        return Money.of(total);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (LottoTicket ticket : tickets) {
            stringBuilder.append(ticket).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}


