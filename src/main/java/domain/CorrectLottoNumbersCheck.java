package domain;

public class CorrectLottoNumbersCheck {

    private static final int IS_CORRECT_LOTTO_NUMBER = 1;
    private static final int IS_NOT_CORRECT_LOTTO_NUMBER = 0;

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
