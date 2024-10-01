package controller;

import domain.*;
import util.InputFromUser;
import util.StringToIntegerConvertor;
import util.WinningLottoNumberSeparator;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final LottosCreator lottosCreator;
    private final LottoCountCalculator lottoCountCalculator;
    private final UpdateWinningLottos updateWinningLottos;
    private final RateOfReturnCalculator rateOfReturnCalculator;

    public LottoController(final LottosCreator lottosCreator, final LottoCountCalculator lottoCountCalculator, final UpdateWinningLottos updateWinningLottos, final RateOfReturnCalculator rateOfReturnCalculator) {
        this.lottosCreator = lottosCreator;
        this.lottoCountCalculator = lottoCountCalculator;
        this.updateWinningLottos = updateWinningLottos;
        this.rateOfReturnCalculator = rateOfReturnCalculator;
    }

    public void startLottoApplication() {
        final int buyingCosts = inputCosts();
        final int lottoCount = lottoCountCalculator.calculateLottoCount(buyingCosts);
        Lottos lottos = makeLottos(lottoCount);

        printLottos(lottos, lottoCount);

        LastWeekWinningLotto lastWeekWinnerLotto = inputLastWeekWinningLottoNumber();

        updateWinningLottos.updateWinningLottos(lottos, lastWeekWinnerLotto);

        printWinningLottosAndRateOfReturn(buyingCosts);
    }

    private int inputCosts() {
        InputView.printBuyingCosts();
        return InputFromUser.inputBuyingCosts();
    }

    private Lottos makeLottos(final int lottoCount) {
        return lottosCreator.createLottos(lottoCount);
    }

    private void printLottos(final Lottos lottos, final int lottoCount) {
        OutputView.printCompleteBuyingLotto(lottoCount);
        printLottos(lottos.getLottos());
    }

    private void printLottos(final List<Lotto> lottos) {
        for (Lotto currentLotto : lottos) {
            OutputView.printBuyingLotto(currentLotto.getLottoNumber());
        }
    }

    private LastWeekWinningLotto inputLastWeekWinningLottoNumber() {
        InputView.printLastWeekWinningLottoNumber();
        final List<String> lastWeekWinningLottoNumber = WinningLottoNumberSeparator.separateWinningLottoNumbers(
                InputFromUser.inputLastWeekWinningLottoNumber()
        );
        InputView.printLastWeekWinningLottoBonusNumber();
        final int bonusNumber = InputFromUser.inputLastWeekWinningLottoBonusNumber();
        return new LastWeekWinningLotto(
                StringToIntegerConvertor.convertStringToInteger(lastWeekWinningLottoNumber), bonusNumber
        );
    }

    private void printWinningLottosAndRateOfReturn(final int buyingCosts) {
        OutputView.printTotalWinning();
        Arrays.stream(WinningLottos.values())
                .forEach(winningLottos ->
                        OutputView.printWinningLottoResult(winningLottos.getCorrectCount(), winningLottos.getPrizeMoney(), winningLottos.getLottoCount(), winningLottos.isSecondPrize())
                );
        OutputView.printRateOfReturn(rateOfReturnCalculator.calculateRateOfReturn(buyingCosts));
    }
}
