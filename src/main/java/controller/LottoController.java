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

        // 구매한 로또의 갯수를 출력합니다.
        OutputView.printAmountMessage(manualCount, autoCount);

        // 생성된 로또를 출력합니다.
        OutputView.printLottos(this.lottoGame.getLottos());

        // 당첨 번호를 입력받습니다.
        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        this.bonusBall = InputView.inputBonusBall();
        this.winningNumbersObj = new WinningNumbers(winningNumbers);
    }

    public void resultLotto() {
        // LottoCalculator 객체 생성
        this.lottoCalculator = new LottoCalculator();

        // 당첨 통계를 계산하고 출력합니다.
        int[] winningStatistics = this.lottoCalculator.calculateWinningStatistics(this.lottoGame.getLottos(), this.winningNumbersObj, this.bonusBall);
        int totalLottoCount = this.lottoGame.getLottos().size();
        double profitRate = LottoCalculator.calculateProfitRate(winningStatistics, totalLottoCount);
        OutputView.printWinningStatistics(winningStatistics, profitRate);
    }
}

