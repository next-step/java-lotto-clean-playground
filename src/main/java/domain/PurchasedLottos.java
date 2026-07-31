package domain;

import java.util.List;

public record PurchasedLottos(
        List<Lotto> lottos,
        int manualLottoCount,
        int autoLottoCount
) {
    public PurchasedLottos {
        lottos = List.copyOf(lottos);
    }
}
