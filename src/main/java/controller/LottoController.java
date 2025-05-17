package controller;

import static domain.constant.LottoConstants.LOTTO_PRICE;

import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.LottoNumber;
import domain.WinningLotto;
import domain.generator.NumberGenerator;
import java.util.ArrayList;
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
            Lotto manualLotto = new Lotto(() -> manualNumbers.stream()
                    .map(LottoNumber::new)
                    .toList());
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
        List<LottoNumber> numbers = inputView.readWinningNumbers().stream()
                .map(LottoNumber::new)
                .toList();
        Lotto winningLotto = new Lotto(numbers);
        LottoNumber bonusNumber = new LottoNumber(inputView.readBonusNumber());

        return new WinningLotto(winningLotto, bonusNumber);
    }
}
