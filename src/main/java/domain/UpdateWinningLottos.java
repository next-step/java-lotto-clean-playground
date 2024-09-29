package domain;

public class UpdateWinningLottos {

    private final CorrectLottoNumbersCheck correctLottoNumbersCheck;

    public UpdateWinningLottos(CorrectLottoNumbersCheck correctLottoNumbersCheck) {
        this.correctLottoNumbersCheck = correctLottoNumbersCheck;
    }

    public void updateWinningLottos(final Lottos lottos, final Lotto lastWeekWinnerLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            updateWinningLotto(lotto, lastWeekWinnerLotto);
        }
    }

    private void updateWinningLotto(final Lotto lotto, final Lotto lastWeekWinnerLotto) {
        final int correctCount = correctLottoNumbersCheck.checkCorrectLottoNumbers(lastWeekWinnerLotto, lotto);
        if (WinningLottos.of(correctCount) != null) {
            WinningLottos.of(correctCount).addWinnerLotto();
        }
    }
}
