package domain.lotto;

public class LottoResult {

    private static final String ALREADY_EXIST = "보너스 번호가 이미 당첨 번호에 존재합니다.";
    
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public LottoResult(LottoTicket winningTicket, LottoNumber bonusNumber) {
        checkBonusBallExist(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void checkBonusBallExist(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException(ALREADY_EXIST);
        }
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        final int match = lottoTicket.contains(winningTicket);
        final boolean hasBonusNumber = lottoTicket.hasBonusNumber(bonusNumber);
        return Prize.findByMatch(match, hasBonusNumber);
    }
}
