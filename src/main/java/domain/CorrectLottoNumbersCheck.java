package domain;

public class CorrectLottoNumbersCheck {

    public int checkCorrectLottoNumbers(final WinningLotto winningLotto, final Lotto lotto) {
        for (final int winningLottoNumber : winningLotto.getLotto().getLottoNumber()) {
            lotto.checkContainedWinningLottoNumbers(winningLottoNumber);
        }
        return lotto.getMatchLottoNumber();
    }

    public boolean checkBonusNumber(final WinningLotto winningLotto, final Lotto lotto) {
        return lotto.checkContainedBonusLottoNumber(winningLotto.getBonusNumber());
    }
}
