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

    protected final List<Ball> balls;

    public Ticket(List<Ball> balls) {
        validateTicket(balls);
        List<Integer> mutableNumberList = new ArrayList<>(balls.stream().map(Ball::getNumber).toList());

        mutableNumberList.sort(Comparator.naturalOrder());

        this.balls = mutableNumberList.stream().map(Ball::new).toList();
    }

    public List<Ball> getBalls() {
        return new ArrayList<>(balls);
    }

    public CorrectCount createCorrectCount(WinnerBalls winnerBalls) {
        int correctCount = 0;
        Ball bonusBall = winnerBalls.getBonusBall();

        for (Ball winnerBall : winnerBalls.getWinnerTicket().getBalls()) {
            correctCount += Boolean.compare(balls.contains(winnerBall), false);
        }

        boolean hasBonusNumber = balls.contains(bonusBall);

        return new CorrectCount(correctCount,  hasBonusNumber);
    }

    private void validateTicket(List<Ball> ticket) {
        if (ticket.isEmpty()) {
            throw new EmptyTicketException("ticket is empty");
        }

        int ticketLength = ticket.size();
        int actualTicketLength = ticket.stream().map(Ball::getNumber).distinct().toList().size();

        if (ticketLength != actualTicketLength) {
            throw new DuplicateNumbersException("duplicate numbers are not allowed in ticket");
        }

        if (ticketLength != TICKET_LENGTH) {
            throw new WrongTicketLengthException("ticket should have " +  TICKET_LENGTH + " numbers");
        }
    }
}
