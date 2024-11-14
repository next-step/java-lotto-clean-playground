package domain;

public class UpdateWinningLottos {

    private final CorrectLottoNumbersCheck correctLottoNumbersCheck;
    private static final int SECOND_PRIZE_MATCH_COUNT = 5;

    public UpdateWinningLottos(final CorrectLottoNumbersCheck correctLottoNumbersCheck) {
        this.correctLottoNumbersCheck = correctLottoNumbersCheck;
    }

    public void updateWinningLottos(final Lottos passiveLottos, final Lottos autoLottos, final WinningLotto winnerLotto, final WinningLottoCount winningLottoCount) {
        for (Lotto lotto : passiveLottos.getLottos()) {
            updateWinningLotto(lotto, winnerLotto, winningLottoCount);
        }
        for (Lotto lotto : autoLottos.getLottos()) {
            updateWinningLotto(lotto, winnerLotto, winningLottoCount);
        }
    }

    private void updateWinningLotto(final Lotto lotto, final WinningLotto lastWeekWinnerLotto, final WinningLottoCount winningLottoCount) {
        final int correctCount = correctLottoNumbersCheck.checkCorrectLottoNumbers(lastWeekWinnerLotto, lotto);
        final boolean isBonusMatched = correctLottoNumbersCheck.checkBonusNumber(lastWeekWinnerLotto, lotto);
        final boolean isSecondPrize = checkSecondPrize(correctCount, isBonusMatched);
        if (!WinningLottosStatus.of(correctCount, isSecondPrize).equals(WinningLottosStatus.NOT_WINNING_LOTTOS)) {
            winningLottoCount.updateWinningLottoCountStatus(WinningLottosStatus.of(correctCount, isSecondPrize));
        }
    }

    private boolean checkSecondPrize(final int correctCount, final boolean isBonusMatched) {
        return correctCount == SECOND_PRIZE_MATCH_COUNT && isBonusMatched;
    }
}
