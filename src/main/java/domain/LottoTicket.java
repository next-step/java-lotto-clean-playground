package domain;

import domain.wrappers.CorrectCount;
import exception.EmptyTicketException;
import exception.NullTicketException;
import exception.WrongNumberInTicketException;
import exception.WrongSizeTicketException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LottoTicket {
    private static final int TICKET_LENGTH = 6;

    private final List<Integer> ticket;

    public LottoTicket(List<Integer> ticket) {
        validateTicket(ticket.stream().distinct().toList());

        List<Integer> mutableTicket = new ArrayList<>(ticket);
        mutableTicket.sort(Comparator.naturalOrder());

        validateTicketNumbers(mutableTicket);

        this.ticket = mutableTicket;
    }

    public List<Integer> getTicket() {
        return ticket;
    }

    public CorrectCount calculateCorrectCount(LottoTicket winnerTicket) {
        int correctCount = 0;

        for (int lottoNumber : winnerTicket.ticket) {
            if (ticket.contains(lottoNumber)) {
                correctCount++;
            }
        }

        return new CorrectCount(correctCount);
    }

    private void validateTicket(List<Integer> ticket) {
        if (ticket == null) {
            throw new NullTicketException("ticket is null");
        }

        if (ticket.isEmpty()) {
            throw new EmptyTicketException("ticket is empty");
        }

        if (ticket.size() != TICKET_LENGTH) {
            throw new WrongSizeTicketException("ticket size should have " +  TICKET_LENGTH + " numbers");
        }
    }

    private void validateTicketNumbers(List<Integer> ticket) {
        if (ticket.get(0) < 1 || ticket.get(TICKET_LENGTH - 1) > 45) {
            throw new WrongNumberInTicketException("wrong number in ticket");
        }
    }


}
