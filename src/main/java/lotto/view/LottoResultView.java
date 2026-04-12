package lotto.view;

import lotto.domain.LottoReceiptResult;
import lotto.domain.LottoResult;

public class LottoResultView {
    private final LottoReceiptResult receiptResult;

    public LottoResultView(LottoReceiptResult receiptResult) {
        this.receiptResult = receiptResult;
    }

    public void printDrawResult() {
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
