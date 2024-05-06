package controller;

import domain.LottoGame;
import domain.Lottos;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;

import static view.OutputView.printAmountMessage;

public class LottoController {
    public void startGame() {
        OutputView.printStartMessage();
        int lottoTotalPrice = InputView.readLottoPrice();
        LottoGame lottoGame = new LottoGame(lottoTotalPrice);
        OutputView.printAmountMessage(lottoGame.calculateLottoAmount());
        OutputView.printLottos(lottoGame.getLottos());

        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        WinningNumbers winningNumbersObj = new WinningNumbers(winningNumbers);

        int[] winningStatistics = lottoGame.calculateWinningStatistics(winningNumbersObj);
        double profitRate = LottoGame.calculateProfitRate(winningStatistics, lottoGame.getLottos().size());
        OutputView.printWinningStatistics(winningStatistics, profitRate);
    }
}