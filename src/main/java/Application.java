import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        PurchaseAmount purchaseAmount = new PurchaseAmount(inputView.readPurchaseAmount());

        int manualLottoCount = inputView.readManualLottoCount();
        int autoLottoCount = purchaseAmount.calculateAutoLottoCount(manualLottoCount);

        List<List<Integer>> manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoCount);

        Lottos lottos = new Lottos(manualLottoNumbers, autoLottoCount);

        outputView.printPurchasedCount(manualLottoCount, autoLottoCount);
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = new WinningNumbers(inputView.readWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(inputView.readBonusNumber());

        winningNumbers.validateBonusNumber(bonusNumber);

        outputView.printLottoStatistics(lottos, winningNumbers, purchaseAmount, bonusNumber);
    }
}
