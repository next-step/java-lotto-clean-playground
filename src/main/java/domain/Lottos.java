package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(PurchaseAmount purchaseAmount) {
        int lottoCount = purchaseAmount.calculateLottoCount();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
