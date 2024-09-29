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

    public LottoController(LottosCreator lottosCreator, LottoCountCalculator lottoCountCalculator, UpdateWinningLottos updateWinningLottos) {
        this.lottosCreator = lottosCreator;
        this.lottoCountCalculator = lottoCountCalculator;
        this.updateWinningLottos = updateWinningLottos;
    }

    public void startLottoApplication() {
        final int lottoCount = inputCosts();

        Lottos lottos = makeLottos(lottoCount);

        printLottos(lottos, lottoCount);

        Lotto lastWeekWinnerLotto = inputLastWeekWinningLottoNumber();

        updateWinningLottos.updateWinningLottos(lottos, lastWeekWinnerLotto);

        printWinningLottos();
    }

    private int inputCosts() {
        InputView.printBuyingCosts();
        return lottoCountCalculator.calculateLottoCount(InputFromUser.inputBuyingCosts());
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

    private Lotto inputLastWeekWinningLottoNumber() {
        InputView.printLastWeekWinningLottoNumber();
        final List<String> lastWeekWinningLottoNumber = WinningLottoNumberSeparator.separateWinningLottoNumbers(
                InputFromUser.inputLastWeekWinningLottoNumber()
        );
        return new Lotto(
                StringToIntegerConvertor.convertStringToInteger(lastWeekWinningLottoNumber)
        );
    }

    private void printWinningLottos() {
        OutputView.printTotalWinning();
        Arrays.stream(WinningLottos.values())
                .forEach(winningLottos ->
                        OutputView.printWinningLottoResult(winningLottos.getCorrectCount(), winningLottos.getPrizeMoney(), winningLottos.getLottoCount())
                );
    }
}
