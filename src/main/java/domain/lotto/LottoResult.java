package domain.lotto;

public class LottoResult {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public LottoResult(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        final int match = lottoTicket.contains(winningTicket);
        final boolean hasBonusNumber = lottoTicket.hasBonusNumber(bonusNumber);
        return Prize.findByMatch(match, hasBonusNumber);
    }
}
