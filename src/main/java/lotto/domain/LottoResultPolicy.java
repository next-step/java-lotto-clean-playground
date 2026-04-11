package lotto.domain;

public interface LottoResultPolicy {
    LottoReceiptResult getResult(WinningLotto win, LottoReceipt receipt);

    LottoResult getResult(WinningLotto win, Lotto lottoRow);
}
