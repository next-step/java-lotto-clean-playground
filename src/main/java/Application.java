import java.util.List;

import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.WinningNumbers;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        int money = InputView.inputMoney();
        int manualCount = InputView.inputManualLottoCount();
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualCount);
        LottoMachine LottoMachine = new LottoMachine();
        Lottos lottos = LottoMachine.buyLotto(money, manualCount, manualLottoNumbers);
        ResultView.outputLotto(lottos, manualCount);

        List<Integer> winningNumberList = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberList, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottos, winningNumbers, money);
        ResultView.outputWinningStatistics(lottoResult);
    }
}
