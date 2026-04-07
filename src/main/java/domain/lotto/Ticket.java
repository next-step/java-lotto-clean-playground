package domain.lotto;

import domain.lotto.wrappers.CorrectCount;
import domain.lotto.exception.DuplicateNumbersException;
import domain.lotto.exception.EmptyTicketException;
import domain.lotto.exception.WrongTicketLengthException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ticket {
    public static final int TICKET_LENGTH = 6;

    protected final List<Number> ticket;

    public Ticket(List<Number> ticket) {
        validateTicket(ticket);
        List<Integer> mutableNumberList = new ArrayList<>(ticket.stream().map(Number::getNumber).toList());

        mutableNumberList.sort(Comparator.naturalOrder());

        this.ticket = mutableNumberList.stream().map(Number::new).toList();
    }

    public List<Number> getTicket() {
        return new ArrayList<>(ticket);
    }

    public CorrectCount createCorrectCount(WinnerTicketPair winnerTicketPair) {
        int correctCount = 0;
        Number bonusNumber = winnerTicketPair.getBonusNumber();

        for (Number winnerNumber : winnerTicketPair.getWinnerTicket().getTicket()) {
            correctCount += Boolean.compare(ticket.contains(winnerNumber), false);
        }

        boolean hasBonusNumber = ticket.contains(bonusNumber);

        return new CorrectCount(correctCount,  hasBonusNumber);
    }

    private void validateTicket(List<Number> ticket) {
        if (ticket.isEmpty()) {
            throw new EmptyTicketException("ticket is empty");
        }

        int ticketLength = ticket.size();
        int actualTicketLength = ticket.stream().map(Number::getNumber).distinct().toList().size();

        if (ticketLength != actualTicketLength) {
            throw new DuplicateNumbersException("duplicate numbers are not allowed in ticket");
        }

        if (ticketLength != TICKET_LENGTH) {
            throw new WrongTicketLengthException("ticket should have " +  TICKET_LENGTH + " numbers");
        }
    }
}
