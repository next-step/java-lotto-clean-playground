package domain;

public class CorrectLottoNumbersCheck {

    public int checkCorrectLottoNumbers(final WinningLotto winningLotto, final Lotto lotto) {
        for (final int winningLottoNumber : winningLotto.getLottoNumber()) {
            lotto.checkContainedWinningLottoNumbers(winningLottoNumber);
        }
        return lotto.getMatchLottoNumber();
    }

    public boolean checkBonusNumber(final WinningLotto lastWeekWinnerLotto, final Lotto lotto) {
        return lotto.getLottoNumber().contains(lastWeekWinnerLotto.getBonusNumber());
    }
}
