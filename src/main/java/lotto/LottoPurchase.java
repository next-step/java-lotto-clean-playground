package lotto;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private final int totalPrice;
    private final int change;

    public LottoPurchase(int totalPrice, LottoMaker lottoMaker) {
        this.totalPrice = totalPrice;

        int numberOfLotto = this.totalPrice / LOTTO_PRICE;
        change = totalPrice % LOTTO_PRICE;

        this.lottos = Lottos.from(numberOfLotto, lottoMaker);
    }

    public LottoReceipt printReceipt() {
        return new LottoReceipt(lottos, totalPrice);
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }

    public int getChange() {
        return change;
    }
}
