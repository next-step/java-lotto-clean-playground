package controller;

import domain.Lotto;
import domain.LottoCalculator;
import domain.LottoGame;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private LottoGame lottoGame;
    private LottoCalculator lottoCalculator; // LottoCalculator 객체 추가
    private WinningNumbers winningNumbersObj;
    private int bonusBall;

    public void startGame() {
        OutputView.printStartMessage();
        int lottoTotalPrice = InputView.readLottoPrice();
        int manualCount = InputView.inputManualCount();

        List<List<Integer>> manualNumbers = InputView.inputManualNumbers(manualCount);
        int autoCount = (lottoTotalPrice - manualCount * Lotto.PRICE_PER_TICKET) / Lotto.PRICE_PER_TICKET;
        this.lottoGame = new LottoGame(lottoTotalPrice, manualCount, autoCount, manualNumbers);

        OutputView.printAmountMessage(manualCount, autoCount);

        OutputView.printLottos(this.lottoGame.getLottos());

        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        this.bonusBall = InputView.inputBonusBall();
        this.winningNumbersObj = new WinningNumbers(winningNumbers);
    }

    public void resultLotto() {
        this.lottoCalculator = new LottoCalculator();

        int[] winningStatistics = this.lottoCalculator.calculateWinningStatistics(this.lottoGame.getLottos(), this.winningNumbersObj, this.bonusBall);
        int totalLottoCount = this.lottoGame.getLottos().size();
        double profitRate = LottoCalculator.calculateProfitRate(winningStatistics, totalLottoCount);
        OutputView.printWinningStatistics(winningStatistics, profitRate);
    }
}