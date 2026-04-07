package domain.lotto;

public class WinnerTicketPair {
    private final Ticket winnerTicket;

    private final Number bonusNumber;

    public WinnerTicketPair(Ticket winnerTicket, Number bonusNumber) {
        this.winnerTicket = winnerTicket;
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(Number bonusNumber) {
        if (winnerTicket.getTicket().contains(bonusNumber)) {
            throw new IllegalArgumentException("that number is already in the winner ticket");
        }
    }

    public Ticket getWinnerTicket() {
        return new Ticket(winnerTicket.getTicket());
    }

    public Number getBonusNumber() {
        return new Number(bonusNumber.getNumber());
    }
}
