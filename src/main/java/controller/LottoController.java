package controller;

import java.util.List;

import domain.LottoDrawResult;
import domain.LottoMachine;
import domain.LottoNumbers;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import util.Parser;
import view.InputView;
import view.ResultView;

public class LottoController {

    public void run() {
        Money money = Parser.parseMoney(InputView.inputMoney());
        Lottos lottos = purchaseLottos(money);
        calculateWinningStatistics(lottos, money);
    }

    private Lottos purchaseLottos(Money money) {
        int manualCount = InputView.inputManualLottoCount();
        List<LottoNumbers> manualLottoNumbers = Parser.parseLottoNumbersList(InputView.inputManualLottoNumbers(manualCount));

        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = lottoMachine.buyLotto(money, manualCount, manualLottoNumbers);

        ResultView.outputLotto(lottos, manualCount);
        return lottos;
    }

    private void calculateWinningStatistics(Lottos lottos, Money money) {
        LottoNumbers winningNumbers = Parser.parseLottoNumbers(InputView.inputWinningNumbers());
        int bonusNumber = InputView.inputBonusNumber();

        LottoDrawResult lottoDrawResult = new LottoDrawResult(winningNumbers, bonusNumber);

        LottoResult lottoResult = new LottoResult(lottos, lottoDrawResult, money);
        ResultView.outputWinningStatistics(lottoResult);
    }
}
