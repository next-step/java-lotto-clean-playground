package domain;

import domain.wrappers.CorrectCount;
import exception.DuplicateNumbersException;
import exception.EmptyTicketException;
import exception.WrongTicketLengthException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static domain.LottoConstants.TICKET_LENGTH;

public class LottoTicket {
    private final List<LottoNumber> ticket;

    public LottoTicket(List<LottoNumber> ticket) {
        validateTicket(ticket);
        List<Integer> mutableNumberList = new ArrayList<>(ticket.stream().map(LottoNumber::getNumber).toList());

        mutableNumberList.sort(Comparator.naturalOrder());

        this.ticket = mutableNumberList.stream().map(LottoNumber::new).toList();
    }

    public List<LottoNumber> getTicket() {
        return new ArrayList<>(ticket);
    }

    public CorrectCount calculateCorrectCount(LottoTicket winnerTicket) {
        int correctCount = 0;
        List<Integer> ticketNumberList = ticket.stream().map(LottoNumber::getNumber).toList();
        correctCount += (int) winnerTicket
                .getTicket()
                .stream()
                .map(LottoNumber::getNumber)
                .filter(ticketNumberList::contains)
                .count();

        return new CorrectCount(correctCount);
    }

    private void validateTicket(List<LottoNumber> ticket) {
        if (ticket.isEmpty()) {
            throw new EmptyTicketException("ticket is empty");
        }

        int ticketLength = ticket.size();
        int actualTicketLength = ticket.stream().map(LottoNumber::getNumber).distinct().toList().size();

        if (ticketLength != actualTicketLength) {
            throw new DuplicateNumbersException("duplicate numbers are not allowed in ticket");
        }

        if (ticketLength != TICKET_LENGTH) {
            throw new WrongTicketLengthException("ticket should have " +  TICKET_LENGTH + " numbers");
        }
    }
}
