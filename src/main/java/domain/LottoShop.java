package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LottoShop {

    public static final BigDecimal PRICE_PER_TICKET = BigDecimal.valueOf(1000);

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

        return new Lottos(allLottos);
    }

    private static void validate(Money money, LottoCount manualCount, List<Lotto> manualLottos) {
        if (money.getPurchasedLottoCount() < manualCount.getLottoCount()) {
            throw new IllegalArgumentException("금액이 수동 로또 개수보다 부족합니다.");
        }

        if (manualCount.getLottoCount() != manualLottos.size()) {
            throw new IllegalArgumentException("수동 로또 개수 만큼 입력해주세요.");
        }
    }
}
