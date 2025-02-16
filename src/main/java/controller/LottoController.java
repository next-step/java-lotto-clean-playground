package controller;

import java.util.List;

import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.WinningNumbers;
import view.InputView;
import view.ResultView;

public class LottoController {

    public void run() {
        int money = InputView.inputMoney();
        Lottos lottos = purchaseLottos(money);
        calculateWinningStatistics(lottos, money);
    }

    private Lottos purchaseLottos(int money) {
        int manualCount = InputView.inputManualLottoCount();
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualCount);
        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = lottoMachine.buyLotto(money, manualCount, manualLottoNumbers);
        ResultView.outputLotto(lottos, manualCount);
        return lottos;
    }

    private void calculateWinningStatistics(Lottos lottos, int money) {
        List<Integer> winningNumberList = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberList, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottos, winningNumbers, money);
        ResultView.outputWinningStatistics(lottoResult);
    }
}
