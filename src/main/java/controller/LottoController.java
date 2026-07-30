package controller;

import domain.LottoSystem;
import domain.LottoWinningStatus;
import domain.RandomLottoNumberGenerator;
import java.util.List;
import view.InputView;
import view.ResultView;

public class LottoController {
  public static void run(){
    final int purchaseAmount = InputView.purchaseAmount();
    final LottoSystem lottoSystem = new LottoSystem(purchaseAmount, new RandomLottoNumberGenerator());
    ResultView.printPurchasedLottos(lottoSystem.purchasedLottoCount(), lottoSystem.purchasedLottoNumbers());

    final List<Integer> winningNumbers = InputView.winningNumbers();
    final LottoWinningStatus lottoWinningStatus = new LottoWinningStatus(winningNumbers, lottoSystem.purchasedLottos(), purchaseAmount);
    ResultView.printMatchingNumbers(lottoWinningStatus.rankTable());
    ResultView.printProfitRate(lottoWinningStatus.profitRate());
  }
}
