package controller;

import domain.*;
import util.InputFromUser;
import util.StringToIntegerConvertor;
import util.LottoNumberSeparator;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final LottoCountCalculator lottoCountCalculator;
    private final UpdateWinningLottos updateWinningLottos;
    private final RateOfReturnCalculator rateOfReturnCalculator;

    public LottoController(final LottoCountCalculator lottoCountCalculator, final UpdateWinningLottos updateWinningLottos, final RateOfReturnCalculator rateOfReturnCalculator) {
        this.lottoCountCalculator = lottoCountCalculator;
        this.updateWinningLottos = updateWinningLottos;
        this.rateOfReturnCalculator = rateOfReturnCalculator;
    }

    public void startLottoApplication() {
        final int buyingCosts = inputCosts();
        final int passiveLottoCount = inputPassiveLottoCount();
        final int autoLottoCount = lottoCountCalculator.calculateLottoCount(buyingCosts, passiveLottoCount);
        final List<String> passiveLottosNumbers = inputPassiveLottos(passiveLottoCount);

        Lottos passiveLottos = makeLottos(
                new PassiveLottosMakeStrategy(passiveLottosNumbers)
        );
        Lottos autoLottos = makeLottos(
                new RandomLottosMakeStrategy(autoLottoCount)
        );

        printAllLottos(passiveLottos, autoLottos);

        WinningLotto lastWeekWinnerLotto = inputLastWeekWinningLottoNumber();

        updateWinningLottos.updateWinningLottos(passiveLottos, autoLottos, lastWeekWinnerLotto);

        printWinningLottosAndRateOfReturn(buyingCosts);
    }

    private int inputCosts() {
        InputView.printBuyingCosts();
        return InputFromUser.inputBuyingCosts();
    }

    private Lottos makeLottos(final LottoMakeStrategy lottoMakeStrategy) {
        return lottoMakeStrategy.makeLottos();
    }

    private void printAllLottos(final Lottos passiveLottos, final Lottos autoLottos) {
        OutputView.printCompleteBuyingLotto(passiveLottos.getLottosCount(), autoLottos.getLottosCount());
        printLottos(passiveLottos.getLottos());
        printLottos(autoLottos.getLottos());
    }

    private void printLottos(final List<Lotto> lottos) {
        for (Lotto currentLotto : lottos) {
            OutputView.printBuyingLotto(currentLotto.getLottoNumber());
        }
    }

    private WinningLotto inputLastWeekWinningLottoNumber() {
        InputView.printLastWeekWinningLottoNumber();
        final List<String> lastWeekWinningLottoNumber = LottoNumberSeparator.separateWinningLottoNumbers(
                InputFromUser.inputLastWeekWinningLottoNumber()
        );
        InputView.printLastWeekWinningLottoBonusNumber();
        final int bonusNumber = InputFromUser.inputLastWeekWinningLottoBonusNumber();
        return new WinningLotto(
                StringToIntegerConvertor.convertStringToInteger(lastWeekWinningLottoNumber), bonusNumber
        );
    }

    private void printWinningLottosAndRateOfReturn(final int buyingCosts) {
        OutputView.printTotalWinning();
        Arrays.stream(WinningLottosStatus.values())
                .forEach(winningLottos ->
                        OutputView.printWinningLottoResult(winningLottos.getCorrectCount(), winningLottos.getPrizeMoney(), winningLottos.getLottoCount(), winningLottos.isSecondPrize())
                );
        OutputView.printRateOfReturn(rateOfReturnCalculator.calculateRateOfReturn(buyingCosts));
    }

    private int inputPassiveLottoCount() {
        InputView.printPassiveLottoCount();
        return InputFromUser.inputPassiveLottoCount();
    }

    private List<String> inputPassiveLottos(final int passiveLottoCount) {
        InputView.printPassiveLottoNumbers();
        return InputFromUser.inputPassiveLottos(passiveLottoCount);
    }
}
