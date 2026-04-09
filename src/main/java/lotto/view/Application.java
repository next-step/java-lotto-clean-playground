package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMaker;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoReceipt;
import lotto.domain.LottoReceiptResult;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;

public class Application {
    private final LottoMaker lottoMaker = new LottoMaker();
    private final ViewInput input = new ViewInput();

    private LottoPurchase purchase;
    private LottoReceipt receipt;
    private WinningLotto winningLotto;
    private LottoReceiptResult receiptResult;

    public void run() {
        purchaseLottoAndPrint();
        readWinningLotto();
        checkLottoResult();
        printLottoResult();
    }

    private void purchaseLottoAndPrint() {
        purchaseLotto();
        printLottoReceipt();
        printPurchasedLotto();
    }

    private void purchaseLotto() {
        System.out.println("구입금액을 입력해 주세요.");

        int totalPrice = input.readTotalPrice();
        purchase = new LottoPurchase(totalPrice, lottoMaker);

        System.out.println();
        System.out.println(purchase.getNumberOfLotto() + "개를 구매했습니다.");
    }

    private void printLottoReceipt() {
        receipt = purchase.printReceipt();
    }

    private void printPurchasedLotto() {
        for (Lotto lottoRow : receipt.lottoRows()) {
            printLottoRow(lottoRow);
        }

        int change = purchase.getChange();
        if (change != 0) {
            System.out.println(change + "원이 남았습니다.");
        }

        System.out.println();
    }

    private void printLottoRow(Lotto lottoRow) {
        List<String> numberStrings = lottoRow.getNumbers().stream()
                .map(LottoNumber::format)
                .toList();
        String str = String.join(", ", numberStrings);
        System.out.println("[" + str + "]");
    }

    private void readWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        LottoNumbers lottoNumbers = input.readLottoNumbers();

        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        LottoNumber bonusNumber = input.readBonusNumber();
        winningLotto = new WinningLotto(lottoNumbers, bonusNumber);

        System.out.println();
    }

    private void checkLottoResult() {
        receiptResult = new LottoReceiptResult(winningLotto, receipt);
    }

    private void printLottoResult() {
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원)- " + receiptResult.getCount(LottoResult.THREE));
        System.out.println("4개 일치 (50000원)- " + receiptResult.getCount(LottoResult.FOUR));
        System.out.println("5개 일치 (1500000원)- " + receiptResult.getCount(LottoResult.FIVE));
        System.out.println("5개 일치, 보너스 볼 일치 (30000000원)- " + receiptResult.getCount(LottoResult.FIVE_BONUS));
        System.out.println("6개 일치 (2000000000원)- " + receiptResult.getCount(LottoResult.SIX));

        System.out.println("총 수익률은 " + receiptResult.getRateOfReturn() + "입니다.");
    }
}
