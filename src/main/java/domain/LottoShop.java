package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    private final NumberGenerator numberGenerator;

    public LottoShop(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos purchase(PurchaseAmount purchaseAmount) {
        return new Lottos(createLottos(purchaseAmount));
    }

    private List<Lotto> createLottos(PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseAmount.calculateLottoCount(); i++) {
            lottos.add(new Lotto(numberGenerator.generate()));
        }
        return lottos;
    }
}
