package lotto.view;

import lotto.domain.LottoPurchase;
import lotto.domain.LottoReceipt;
import lotto.domain.LottoReceiptResult;
import lotto.domain.WinningLotto;

public class Application {
    private final ViewInput input = new ViewInput();
    private final LottoPurchaseView purchaseView = new LottoPurchaseView(input);
    private final LottoWinnerInputView lottoWinnerInputView = new LottoWinnerInputView(input);

    private LottoReceipt receipt;
    private WinningLotto winningLotto;

    public void run() {
        purchaseLottoAndPrint();
        readWinningLotto();
        printLottoResult();
    }

    private void purchaseLottoAndPrint() {
        purchaseView.purchaseLotto();
        LottoPurchase purchase = purchaseView.getPurchase();

        receipt = purchase.printReceipt();

        LottoReceiptView receiptView = new LottoReceiptView(purchase, receipt);
        receiptView.printReceipt();
    }

    private void readWinningLotto() {
        winningLotto = lottoWinnerInputView.readWinningLotto();
    }

    private void printLottoResult() {
        LottoReceiptResult receiptResult = new LottoReceiptResult(winningLotto, receipt);
        LottoResultView resultView = new LottoResultView(receiptResult);
        resultView.printDrawResult();
    }
}
