package domain.lotto;

public class LottoResult {

    private final LottoTicket winningTicket;

    public LottoResult(LottoTicket winningTicket) {
        this.winningTicket = winningTicket;
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        final int match = lottoTicket.contains(winningTicket);
        return Prize.findByMatch(match);
    }
}
