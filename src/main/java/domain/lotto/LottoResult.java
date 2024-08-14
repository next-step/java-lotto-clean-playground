package domain.lotto;

import domain.common.ExceptionMessage;

public class LottoResult {

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public LottoResult(LottoTicket winningTicket, LottoNumber bonusNumber) {
        checkBonusBallExist(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void checkBonusBallExist(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_BONUS_NUMBER_ALREADY_EXIST);
        }
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        final int match = lottoTicket.getCorrectCount(winningTicket);
        final boolean hasBonusNumber = lottoTicket.hasBonusNumber(bonusNumber);
        return Prize.findByMatch(match, hasBonusNumber);
    }
}
