package controller;

import domain.LottoMatchResult;
import domain.LottoMatchResults;
import domain.LottoSystem;
import domain.RandomLottoNumberGenerator;
import domain.WinningNumbers;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
  public static void run() {
    final int purchaseAmount = InputView.purchaseAmount();
    final LottoSystem lottoSystem = purchaseLotto(purchaseAmount);
    final WinningNumbers winningNumbers = createWinningNumbers();
    final LottoMatchResults lottoMatchResults = createLottoMatchResults(lottoSystem, winningNumbers, purchaseAmount);
    printLottoResult(lottoMatchResults);
  }

  private static LottoSystem purchaseLotto(int purchaseAmount) {
    final LottoSystem lottoSystem = new LottoSystem(purchaseAmount, new RandomLottoNumberGenerator());
    ResultView.printPurchasedLottos(lottoSystem.purchasedLottoCount(), lottoSystem.purchasedLottoNumbers());
    return lottoSystem;
  }

  private static WinningNumbers createWinningNumbers() {
    final List<Integer> winningNumberValues = InputView.winningNumbers();
    final int bonusNumber = InputView.bonusNumber();
    return new WinningNumbers(winningNumberValues, bonusNumber);
  }

  private static LottoMatchResults createLottoMatchResults(LottoSystem lottoSystem, WinningNumbers winningNumbers, int purchaseAmount) {
    final List<LottoMatchResult> matchResults = lottoSystem.purchasedLottos().createLottoMatchResult(winningNumbers);
    return new LottoMatchResults(matchResults, purchaseAmount);
  }

  private static void printLottoResult(LottoMatchResults lottoMatchResults) {
    ResultView.printMatchingNumbers(lottoMatchResults.rankTable());
    ResultView.printProfitRate(lottoMatchResults.profitRate());
  }
}
