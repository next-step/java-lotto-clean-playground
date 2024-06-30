package domain.lotto;

public class LottoResult {

    private static final int LOSING_VALUE = 3;
    private final LottoTicket winningTicket;

    public LottoResult(LottoTicket winningTicket) {
        this.winningTicket = winningTicket;
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        int match = 0;
        for (Integer winningNumber : winningTicket.getLottoNumbers()) {
            if (lottoTicket.contains(winningNumber)) {
                match++;
            }
        }

        if (match < LOSING_VALUE) {
            return Prize.LOSING_TICKET;
        }

        return Prize.findByMatch(match);
    }
}
