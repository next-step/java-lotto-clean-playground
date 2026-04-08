package domain.lotto;

public class WinnerBalls {
    private final Ticket winnerTicket;

    private final Number bonusBall;

    public WinnerBalls(Ticket winnerTicket, Number bonusBall) {
        this.winnerTicket = winnerTicket;
        validate(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validate(Number bonusNumber) {
        if (winnerTicket.getTicket().contains(bonusNumber)) {
            throw new IllegalArgumentException("that number is already in the winner ticket");
        }
    }

    public Ticket getWinnerTicket() {
        return new Ticket(winnerTicket.getTicket());
    }

    public Number getBonusBall() {
        return new Number(bonusBall.getNumber());
    }
}
