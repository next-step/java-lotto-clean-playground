package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.view.LottoPriceException;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottoRows = new ArrayList<>();
    private final int totalPrice;
    private final int change;

    public LottoPurchase(int totalPrice, LottoMaker lottoMaker) {
        this.totalPrice = totalPrice;

        int numberOfLotto = totalPrice / LOTTO_PRICE;
        change = totalPrice % LOTTO_PRICE;

        if (numberOfLotto < 1) {
            throw new LottoPriceException.Illegal("로또를 1장은 사야 합니다.");
        }

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
