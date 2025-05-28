package domain;

import domain.generator.LottoGenerator;

import java.util.*;


public class LottoManager {
    //전체적인 로또를 구매하는 과정을 관리
    private static final Money PRICE_PER_LOTTO = new Money(1000);
    private final LottoGenerator generator;

    public LottoManager(LottoGenerator generator) {
        this.generator = generator;
    }

    public Money purchaseManualLottos(Money money, int manualCount) {
        Money totalManualPrice = PRICE_PER_LOTTO.multiplyBy(manualCount);

        if (money.value() < totalManualPrice.value()) {
            throw new IllegalArgumentException("수동 로또 구입 금액이 부족합니다.");
        }

        return money.minus(totalManualPrice);
    }

    public Lottos purchaseAutoLottos(Money remainingMoney) {
        int autoCount = remainingMoney.divideBy(PRICE_PER_LOTTO);
        List<Lotto> autoLottos = generateLottos(autoCount);
        return new Lottos(autoLottos);
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generator.generate());
        }
        return lottos;
    }
}

