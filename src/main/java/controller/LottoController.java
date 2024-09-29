package controller;

import domain.Lotto;
import domain.LottoCountCalculator;
import domain.Lottos;
import domain.LottosCreator;
import util.InputFromUser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final LottosCreator lottosCreator;
    private final LottoCountCalculator lottoCountCalculator;

    public LottoController(LottosCreator lottosCreator, LottoCountCalculator lottoCountCalculator) {
        this.lottosCreator = lottosCreator;
        this.lottoCountCalculator = lottoCountCalculator;
    }

    public void startLottoApplication() {
        final int lottoCount = inputCosts();

        Lottos lottos = makeLottos(lottoCount);

        printLottos(lottos, lottoCount);
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
}
