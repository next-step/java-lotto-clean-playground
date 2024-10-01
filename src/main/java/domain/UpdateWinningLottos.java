package domain;

public class UpdateWinningLottos {

    private final CorrectLottoNumbersCheck correctLottoNumbersCheck;

    public UpdateWinningLottos(final CorrectLottoNumbersCheck correctLottoNumbersCheck) {
        this.correctLottoNumbersCheck = correctLottoNumbersCheck;
    }

    public void updateWinningLottos(final Lottos lottos, final LastWeekWinningLotto lastWeekWinnerLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            updateWinningLotto(lotto, lastWeekWinnerLotto);
        }
    }

    private void updateWinningLotto(final Lotto lotto, final LastWeekWinningLotto lastWeekWinnerLotto) {
        final int correctCount = correctLottoNumbersCheck.checkCorrectLottoNumbers(lastWeekWinnerLotto, lotto);
        if (WinningLottos.of(correctCount) != null) {
            WinningLottos.of(correctCount).addWinnerLotto();
        }
    }
}
