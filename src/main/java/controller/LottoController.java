package controller;

import static domain.constant.LottoConstants.LOTTO_PRICE;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Numbers;
import domain.WinningLotto;
import domain.generator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
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

        List<Lotto> lottos = new ArrayList<>();
        resultView.printLottoCount(count);
        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto(numberGenerator);
            resultView.printLottoNumbers(lotto);
            lottos.add(lotto);
        }

        return new Lottos(lottos);
    }

    private WinningLotto setUpWinningLotto() {
        Numbers winningNumbers = new Numbers(inputView.readWinningNumbers());
        return new WinningLotto(winningNumbers);
    }
}
