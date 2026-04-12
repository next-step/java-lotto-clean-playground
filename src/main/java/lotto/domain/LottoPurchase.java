package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final int totalPrice;
    private final int lottoCount;
    private final int change;

    private final LottoMaker lottoMaker;
    private final List<Lotto> lottoRows = new ArrayList<>();

    public LottoPurchase(int totalPrice, LottoMaker lottoMaker) {
        this.totalPrice = totalPrice;
        lottoCount = totalPrice / LOTTO_PRICE;
        change = totalPrice % LOTTO_PRICE;

        this.lottoMaker = lottoMaker;

        if (lottoCount < 1) {
            throw new LottoPurchaseException.IllegalCount("로또를 1장은 사야 합니다.");
        }
    }

    public void purchaseManually(LottoNumbers numbers) {
        if (lottoCount <= lottoRows.size()) {
            throw new LottoPurchaseException.IllegalCount("지불한 금액보다 더 구입하려 합니다.");
        }

        Lotto lotto = new Lotto(numbers);
        lottoRows.add(lotto);
    }

    public LottoReceipt printReceipt() {
        while (lottoRows.size() < lottoCount) {
            Lotto lotto = lottoMaker.makeLotto();
            lottoRows.add(lotto);
        }

        return new LottoReceipt(lottoRows, totalPrice);
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public int getChange() {
        return change;
    }
}
