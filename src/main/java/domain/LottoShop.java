package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {

    public static final int PRICE_PER_TICKET = 1000;

    private final LottoMachine lottoMachine;

    public LottoShop(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public Lottos purchaseLottos(Money money, LottoCount manualCount, List<Lotto> manualLottos) {
        validate(money, manualCount, manualLottos);

        LottoCount autoLottoCount = LottoCount.from(money.getPurchasedLottoCount() - manualCount.getLottoCount());
        List<Lotto> autoLottos = lottoMachine.generateLottos(autoLottoCount);
        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(autoLottos);

        return LottoPurchase.createLottoPurchase(money, manualCount, allLottos);
    }
}
