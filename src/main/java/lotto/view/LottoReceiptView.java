package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchase;
import lotto.domain.LottoReceipt;

public class LottoReceiptView {
    private final LottoPurchase purchase;
    private final LottoReceipt receipt;

    public LottoReceiptView(LottoPurchase purchase, LottoReceipt receipt) {
        this.purchase = purchase;
        this.receipt = receipt;
    }

    public void printReceipt() {
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
}
