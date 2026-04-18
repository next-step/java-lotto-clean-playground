package lotto;

public class Main {
    private static final LottoInput LOTTO_INPUT = new LottoInput();
    private static final LottoDisplay LOTTO_DISPLAY = new LottoDisplay();
    private static final LottoPlay LOTTO_PLAY = new LottoPlay();

    public static void main(String[] args) {
        int price = 0;
        while (price < 1000) {
            price = LOTTO_INPUT.inputPrice();
        }

        LottoPurchase purchase = LOTTO_INPUT.buyLottos(price);
        LottoReceipt receipt = purchase.getReceipt();
        LOTTO_DISPLAY.displayReceiptInfo(receipt, purchase.getChange());

        LOTTO_DISPLAY.displayResult(LOTTO_PLAY.runDraw(receipt));
    }
}
