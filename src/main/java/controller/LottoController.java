package controller;

import model.*;
import util.LottoGenerator;
import util.RandomLottoGenerator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private final LottoGenerator generator = new RandomLottoGenerator();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoResultAnalyzer analyzer = new LottoResultAnalyzer();


    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        ManualCount manualCount = inputManualCount(purchaseAmount);
        AutoCount autoCount = new AutoCount(calculateAutoCount(purchaseAmount, manualCount));

        LottoTicket lottoTicket = generateTickets(manualCount, autoCount);
        printTickets(lottoTicket, manualCount, autoCount);
        analyzeResult(lottoTicket, purchaseAmount);
    }

    private PurchaseAmount getPurchaseAmount() {
        return new PurchaseAmount(inputView.inputPurchaseAmount());
    }

    private ManualCount inputManualCount(PurchaseAmount purchaseAmount) {
        return new ManualCount(inputView.inputManualLottoCount(), purchaseAmount);
    }

    private LottoTicket generateTickets(ManualCount manualCount, AutoCount autoCount) {
        List<String> manualInputs = inputView.inputManualLottoNumbers(manualCount);

        ManualLottoTicket manualTicket = new ManualLottoTicket(manualInputs);
        AutoLottoTicket autoTicket = new AutoLottoTicket(generator, autoCount);

        return LottoTicket.merge(manualTicket, autoTicket);
    }

    private void printTickets(LottoTicket lottoTicket, ManualCount manualCount, AutoCount autoCount) {
        outputView.printPurchaseCount(manualCount, autoCount);
        outputView.printLottoNumbers(lottoTicket);
    }

    private void analyzeResult(LottoTicket lottoTicket, PurchaseAmount purchaseAmount) {
        String winningNums = inputView.inputWinningNumbers();
        String bonusNum = inputView.inputBonusNumber();

        WinningNumbers winningNumbers = new WinningNumbers(winningNums, bonusNum);

        analyzer.analyze(lottoTicket, winningNumbers);
        outputView.printStatistics(analyzer.getResult(), analyzer.calculateProfitRate(purchaseAmount));
    }

    private int calculateAutoCount(PurchaseAmount purchaseAmount, ManualCount manualCount) {
        return purchaseAmount.getTotalLottoCount() - manualCount.getManualCount();
    }
}
