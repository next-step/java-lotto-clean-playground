package lotto.controller;

import java.util.List;
import lotto.domain.*;
import lotto.ui.InputView;
import lotto.ui.OutputView;
import lotto.util.InputParser;

public class LottoController {

  private final LottoStore lottoStore = new LottoStore();

  public void run() {
    final Long inputPayment = InputView.inputPayment();

    final Payment payment = InputParser.parsePayment(inputPayment);

    Lottos userLottos = processLottoGeneration(payment);

    LottoResultChecker resultChecker = processLottoResult();

    displayResults(userLottos, resultChecker, payment);
  }

  private Lottos processLottoGeneration(Payment payment) {
    final int manualLottoCount = InputView.inputManualLottoCount();
    final List<String> manualLottoStrings = InputView.inputManualLottoStrings(manualLottoCount);

    final Lottos manualLottos = InputParser.parseLottos(manualLottoStrings);

    final Lottos userLottos = lottoStore.generateLottosWithManualLottos(manualLottos, payment);
    OutputView.printLottos(userLottos);

    return userLottos;
  }

  private LottoResultChecker processLottoResult() {
    final LottoResult lottoResult = InputView.inputLottoResult();
    final LottoNumber bonusBall = InputView.inputBonusBall();

    return new LottoResultChecker(lottoResult, bonusBall);
  }

  private void displayResults(Lottos userLottos, LottoResultChecker checker, Payment payment) {
    final WinningResult winningResult = checker.matchLottos(userLottos);

    OutputView.printResults(winningResult);
    OutputView.printPrize(winningResult, payment);
  }
}
