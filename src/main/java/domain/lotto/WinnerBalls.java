package domain.lotto;

public class WinnerBalls {
    private final Ticket winnerTicket;

    private final Ball bonusBall;

    public WinnerBalls(Ticket winnerTicket, Ball bonusBall) {
        this.winnerTicket = winnerTicket;
        validate(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validate(Ball bonusBall) {
        if (winnerTicket.getBalls().contains(bonusBall)) {
            throw new IllegalArgumentException("that number is already in the winner ticket");
        }
    }

    public Ticket getWinnerTicket() {
        return new Ticket(winnerTicket.getBalls());
    }

    public Ball getBonusBall() {
        return new Ball(bonusBall.getNumber());
    }
}
