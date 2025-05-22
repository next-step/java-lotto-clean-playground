package controller;

import static domain.constant.LottoConstants.LOTTO_PRICE;

import domain.LottoResult;
import domain.Lottos;
import domain.WinningLotto;
import domain.generator.NumberGenerator;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
    private static final String ERROR_INSUFFICIENT_AMOUNT = "금액이 부족합니다.";
    private final InputView inputView;
    private final ResultView resultView;
    private final NumberGenerator numberGenerator;

    public LottoController(InputView inputView, ResultView resultView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        Lottos lottos = setUpLottos();
        WinningLotto winningLotto = setUpWinningLotto();
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);
        resultView.printResult(lottoResult);
    }

    private Lottos setUpLottos() {
        int amount = inputView.readAmount();
        int count = amount / LOTTO_PRICE;
        int manualCount = inputView.readManualLottoCount();
        if (manualCount > count) {
            throw new IllegalArgumentException(ERROR_INSUFFICIENT_AMOUNT);
        }
        int autoCount = count - manualCount;

        List<List<Integer>> manualNumbers = inputView.readManualNumbers(manualCount);
        Lottos lottos = Lottos.create(manualNumbers, autoCount, numberGenerator);

        resultView.printLottoCount(manualCount, autoCount);
        resultView.printLottos(lottos);
        return lottos;
    }

    private WinningLotto setUpWinningLotto() {
        return WinningLotto.create(
                inputView.readWinningNumbers(),
                inputView.readBonusNumber()
        );
    }
}
