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

    public Lottos purchaseLottos(Money money) {
        int lottoCount = money.divideBy(PRICE_PER_LOTTO);
        List<Lotto> lottos = generateLottos(lottoCount);
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

