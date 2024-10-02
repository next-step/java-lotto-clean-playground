package controller;

import domain.*;
import util.InputFromUser;
import util.StringToIntegerConvertor;
import util.LottoNumberSeparator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        final int passiveLottoCount = inputPassiveLottoCount();
        final int lottoCount = lottoCountCalculator.calculateLottoCount(buyingCosts, passiveLottoCount);
        Lottos passiveLottos = makePassiveLottos(passiveLottoCount);
        Lottos autoLottos = makeLottos(lottoCount);
        Lottos lottos = mergeLottos(passiveLottos, autoLottos);

        printLottos(lottos, lottoCount, passiveLottoCount);

        LastWeekWinningLotto lastWeekWinnerLotto = inputLastWeekWinningLottoNumber();

        updateWinningLottos.updateWinningLottos(lottos, lastWeekWinnerLotto);

        printWinningLottosAndRateOfReturn(buyingCosts);
    }

    private Lottos mergeLottos(Lottos passiveLottos, Lottos autoLottos) {
        List<Lotto> lottos = new ArrayList<Lotto>();
        lottos.addAll(passiveLottos.getLottos());
        lottos.addAll(autoLottos.getLottos());
        return new Lottos(lottos);
    }

    private int inputCosts() {
        InputView.printBuyingCosts();
        return InputFromUser.inputBuyingCosts();
    }

    private Lottos makeLottos(final int lottoCount) {
        return lottosCreator.createLottos(lottoCount);
    }

    private void printLottos(final Lottos lottos, final int lottoCount, final int passiveLottoCount) {
        OutputView.printCompleteBuyingLotto(passiveLottoCount, lottoCount);
        printLottos(lottos.getLottos());
    }

    private void printLottos(final List<Lotto> lottos) {
        for (Lotto currentLotto : lottos) {
            OutputView.printBuyingLotto(currentLotto.getLottoNumber());
        }
    }

    private LastWeekWinningLotto inputLastWeekWinningLottoNumber() {
        InputView.printLastWeekWinningLottoNumber();
        final List<String> lastWeekWinningLottoNumber = LottoNumberSeparator.separateWinningLottoNumbers(
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

    private int inputPassiveLottoCount() {
        InputView.printPassiveLottoCount();
        return InputFromUser.inputPassiveLottoCount();
    }

    private Lottos makePassiveLottos(final int passiveLottoCount) {
        InputView.printPassiveLottoNumbers();
        return new Lottos(
                IntStream.range(0, passiveLottoCount)
                        .mapToObj(i ->
                                new Lotto(InputFromUser.inputPassiveLottos())
                        ).toList()
        );
    }
}
