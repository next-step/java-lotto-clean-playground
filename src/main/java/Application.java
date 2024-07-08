import java.util.List;

import domain.Lotto;
import domain.LottoStatus;
import domain.WinningsCalculator;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {

        int lottoCount = InputView.inputPurchasePrice();
        int manualLottoCount = InputView.inputManualLotto();
        int autoLottoCount = lottoCount - manualLottoCount;
        List<Lotto> lottoList = InputView.inputManualLottoNumber(manualLottoCount);
        LottoStatus lottoStatus = new LottoStatus(lottoList);
        lottoStatus.addAutoLotto(autoLottoCount);

        ResultView.printLottoNumbers(lottoStatus, manualLottoCount);

        lottoStatus.setWinningNumber(InputView.inputWinningNumber());
        lottoStatus.setBonusNumber(InputView.inputBonusNumber());

        WinningsCalculator winningsCalculator = new WinningsCalculator();
        winningsCalculator.updateWinningsResult(lottoStatus);
        ResultView.printWinningState(winningsCalculator.getWinningsResult(), winningsCalculator.getProfit());
    }
}
