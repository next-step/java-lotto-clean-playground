package controller;

import java.util.List;
import java.util.Scanner;
import model.Lotto;
import model.Lottos;
import model.WinningLotto;
import view.InputHandler;
import view.OutputHandler;
import model.Money;

public class LottoStore {

    private final InputHandler inputHandler;
    private final LottoManager lottoManager;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        LottoManager lottoManager = new LottoManager();

        new LottoStore(inputHandler, lottoManager).run();
    }

    public LottoStore(InputHandler inputHandler, LottoManager lottoManager) {
        this.inputHandler = inputHandler;
        this.lottoManager = lottoManager;
    }

    private void run() {
        Money money = requestMoney();
        List<Lotto> manualLottos = requestManualLottos(money);
        Lottos lottos = createLottos(money, manualLottos);
        printPurchaseResult(lottos);

        WinningLotto winningLotto = requestWinningLotto();
        printLottoResult(lottos, winningLotto, money);
    }

    private Money requestMoney() {
        return inputHandler.inputMoney();
    }

    private List<Lotto> requestManualLottos(Money money) {
        int manualCount = inputHandler.inputManualCount(money);
        return inputHandler.inputManualLottos(manualCount);
    }

    private Lottos createLottos(Money money, List<Lotto> manualLottos) {
        return lottoManager.createLottos(money, manualLottos.size(), manualLottos);
    }

    private WinningLotto requestWinningLotto() {
        List<Integer> winningNumbers = inputHandler.inputWinningLotto();
        int bonusNumber = inputHandler.inputBonusNumber();
        return lottoManager.createWinningLotto(winningNumbers, bonusNumber);
    }

    private void printPurchaseResult(Lottos lottos) {
        int manualCount = lottos.countManual();
        int autoCount = lottos.countAuto();
        OutputHandler.printPurchaseResult(manualCount, autoCount, lottos.getLottos());
    }

    private void printLottoResult(Lottos lottos, WinningLotto winningLotto, Money money) {
        var result = lottos.countResult(winningLotto);
        long totalPrize = lottos.calculateTotalPrize(winningLotto);
        double rate = money.calculateRate(totalPrize);
        OutputHandler.printStatistics(result, rate);
    }
}
