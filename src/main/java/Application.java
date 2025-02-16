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
        LottoMachine LottoMachine = new LottoMachine();
        Lottos lottos = LottoMachine.buyLotto(money);
        ResultView.outputLotto(lottos);

        List<Integer> winningNumberList = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberList, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottos, winningNumbers, money);
        ResultView.outputWinningStatistics(lottoResult);
    }
}
