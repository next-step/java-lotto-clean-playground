package domain;

import strategy.LottoNumberGenerator;

public class LottoStore {

    private final LottoNumberGenerator generator;

    public LottoStore(final LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public Lottos buy(final PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.getCount();
        return Lottos.generate(count, generator);
    }
}
