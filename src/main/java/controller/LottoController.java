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
        int bonusBall = InputView.inputBonusBall();
        WinningNumbers winningNumbersObj = new WinningNumbers(winningNumbers);

        int[] winningStatistics = lottoGame.calculateWinningStatistics(winningNumbersObj, bonusBall);
        int totalLottoCount = lottoGame.getLottos().size();
        double profitRate = LottoGame.calculateProfitRate(winningStatistics, totalLottoCount);

        OutputView.printWinningStatistics(winningStatistics, profitRate);
    }
}