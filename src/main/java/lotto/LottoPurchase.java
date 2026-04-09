package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottoRows = new ArrayList<>();
    private final int totalPrice;
    private final int change;

    public LottoPurchase(int totalPrice, LottoMaker lottoMaker) {
        this.totalPrice = totalPrice;

        int numberOfLotto = totalPrice / LOTTO_PRICE;
        change = totalPrice % LOTTO_PRICE;

        for (int n = 0; n < numberOfLotto; n++) {
            lottoRows.add(lottoMaker.makeLotto());
        }
    }

    public LottoReceipt printReceipt() {
        return new LottoReceipt(lottoRows, totalPrice);
    }

    public int getNumberOfLotto() {
        return lottoRows.size();
    }

    public int getChange() {
        return change;
    }
}
