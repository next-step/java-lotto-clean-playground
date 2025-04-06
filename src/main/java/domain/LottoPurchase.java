package domain;

import java.util.Collections;
import java.util.List;

public class LottoPurchase {

    private final Money money;
    private final LottoCount manualCount;
    private final List<Lotto> lottos;

    private LottoPurchase(Money money, LottoCount manualCount, List<Lotto> lottos) {
        this.money = money;
        this.manualCount = manualCount;
        this.lottos = lottos;
    }

    public static LottoPurchase createLottoPurchase(Money money, LottoCount manualCount, List<Lotto> lottos) {
        return new LottoPurchase(money, manualCount, lottos);
    }

    public LottoCount getManualCount() {
        return manualCount;
    }

    public LottoCount getAutoCount() {
        return LottoCount.from(money.getPurchasedLottoCount() - manualCount.getLottoCount());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public Money getMoney() {
        return money;
    }
}
