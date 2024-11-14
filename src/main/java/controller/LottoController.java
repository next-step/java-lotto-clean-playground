package controller;

import domain.*;
import util.InputFromUser;
import util.LottoNumberConvertor;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final UpdateWinningLottos updateWinningLottos;
    private final LottoCalculator lottoCalculator;

    public LottoController(final UpdateWinningLottos updateWinningLottos, final LottoCalculator lottoCalculator) {
        this.updateWinningLottos = updateWinningLottos;
        this.lottoCalculator = lottoCalculator;
    }

    public void startLottoApplication() {
        final int buyingCosts = inputCosts();
        final int passiveLottoCount = inputPassiveLottoCount();
        final int autoLottoCount = lottoCalculator.calculateAutoLottoCount(buyingCosts, passiveLottoCount);

        Lottos passiveLottos = makeLottos(new PassiveLottosMakeStrategy(inputPassiveLottos(passiveLottoCount)));
        Lottos autoLottos = makeLottos(new RandomLottosMakeStrategy(autoLottoCount));
        printAllLottos(passiveLottos, autoLottos);

        final WinningLottoCount winningLottoCount = new WinningLottoCount();
        updateWinningLottos.updateWinningLottos(passiveLottos, autoLottos, inputLastWeekWinningLottoNumber(), winningLottoCount);
        printWinningLottosAndRateOfReturn(buyingCosts, winningLottoCount);
    }

    private int inputCosts() {
        InputView.printBuyingCosts();
        return InputFromUser.inputBuyingCosts();
    }

    private int inputPassiveLottoCount() {
        InputView.printPassiveLottoCount();
        return InputFromUser.inputPassiveLottoCount();
    }

    private List<String> inputPassiveLottos(final int passiveLottoCount) {
        InputView.printPassiveLottoNumbers();
        return InputFromUser.inputPassiveLottos(passiveLottoCount);
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
        final List<Integer> winningLotto = LottoNumberConvertor.convert(InputFromUser.inputLastWeekWinningLottoNumber());

        InputView.printLastWeekWinningLottoBonusNumber();
        final int bonusNumber = InputFromUser.inputLastWeekWinningLottoBonusNumber();

        return new WinningLotto(winningLotto, bonusNumber);
    }

    private void printWinningLottosAndRateOfReturn(final int buyingCosts, final WinningLottoCount winningLottoCount) {
        OutputView.printTotalWinning();
        Arrays.stream(WinningLottosStatus.values())
                .filter(winningLottosStatus -> winningLottosStatus != WinningLottosStatus.NOT_WINNING_LOTTOS)
                .forEach(winningLottosStatus ->
                        OutputView.printWinningLottoResult(winningLottosStatus.getCorrectCount(), winningLottosStatus.getPrizeMoney(), winningLottoCount.getWinningLottoCountStatus().get(winningLottosStatus), winningLottosStatus.isSecondPrize())
                );
        OutputView.printRateOfReturn(lottoCalculator.calculateRateOfReturn(buyingCosts, winningLottoCount));
    }

}
