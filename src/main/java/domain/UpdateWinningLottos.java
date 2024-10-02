package domain;

public class UpdateWinningLottos {

    private final CorrectLottoNumbersCheck correctLottoNumbersCheck;
    private static final int SECOND_PRIZE_MATCH_COUNT = 5;

    public UpdateWinningLottos(final CorrectLottoNumbersCheck correctLottoNumbersCheck) {
        this.correctLottoNumbersCheck = correctLottoNumbersCheck;
    }

    public void updateWinningLottos(final Lottos lottos, final WinningLotto lastWeekWinnerLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            updateWinningLotto(lotto, lastWeekWinnerLotto);
        }
    }

    private void updateWinningLotto(final Lotto lotto, final WinningLotto lastWeekWinnerLotto) {
        final int correctCount = correctLottoNumbersCheck.checkCorrectLottoNumbers(lastWeekWinnerLotto, lotto);
        final boolean isBonusMatched = correctLottoNumbersCheck.checkBonusNumber(lastWeekWinnerLotto, lotto);
        final boolean isSecondPrize = checkSecondPrize(correctCount, isBonusMatched);
        if (WinningLottosStatus.of(correctCount, isSecondPrize) != null) {
            WinningLottosStatus.of(correctCount, isSecondPrize).addWinnerLotto();
        }
    }

    private boolean checkSecondPrize(final int correctCount, final boolean isBonusMatched) {
        return correctCount == SECOND_PRIZE_MATCH_COUNT && isBonusMatched;
    }
}
