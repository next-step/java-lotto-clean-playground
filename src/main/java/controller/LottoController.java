package controller;

import static domain.constant.LottoConstants.LOTTO_PRICE;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.Number;
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
        int manualCount = inputView.readManualLottoCount();
        int autoCount = count - manualCount;

        List<Lotto> lottoList = new ArrayList<>();
        List<List<Integer>> manualNumbersList = inputView.readManualNumbers(manualCount);
        setUpManualLottos(manualNumbersList, lottoList);
        setUpAutoLottos(autoCount, lottoList);
        Lottos lottos = new Lottos(lottoList);

        resultView.printLottoCount(manualCount, autoCount);
        resultView.printLottos(lottos);
        return lottos;
    }

    private void setUpManualLottos(List<List<Integer>> manualNumbersList, List<Lotto> lottos) {
        for (List<Integer> manualNumbers : manualNumbersList) {
            Lotto manualLotto = new Lotto(() -> {
                List<Number> numberList = manualNumbers.stream()
                        .map(Number::new)
                        .toList();
                return new Numbers(numberList);
            });
            lottos.add(manualLotto);
        }
    }

    private void setUpAutoLottos(int autoCount, List<Lotto> lottos) {
        for (int i = 0; i < autoCount; i++) {
            Lotto autoLotto = new Lotto(numberGenerator);
            lottos.add(autoLotto);
        }
    }

    private WinningLotto setUpWinningLotto() {
        List<Number> numbers = inputView.readWinningNumbers().stream()
                .map(Number::new)
                .toList();
        Numbers winningNumbers = new Numbers(numbers);
        Number bonusNumber = new Number(inputView.readBonusNumber());

        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
