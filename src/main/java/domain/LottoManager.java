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

    public Lottos purchaseLottos(Money money, int manualCount) {
        int totalCount = money.divideBy(PRICE_PER_LOTTO);
        int autoCount = totalCount - manualCount;

        if (autoCount < 0) {
            throw new IllegalArgumentException("수동 로또 수가 총 구매 가능 수보다 많을 수 없습니다.");
        }

        List<Lotto> lottos = generateLottos(autoCount);
        return new Lottos(lottos);
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generator.generate());
        }
        return lottos;
    }
}

