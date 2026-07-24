package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;

    private final PurchaseAmount purchaseAmount;
    private final LottoGenerator lottoGenerator;

    public LottoPurchase(PurchaseAmount purchaseAmount, LottoGenerator lottoGenerator) {
        this.purchaseAmount = purchaseAmount;
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueLottos() {
        int lottoCount = purchaseAmount.getAmount() / LOTTO_PRICE;

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(lottoGenerator.generate());
        }

        return lottos;
    }
}
