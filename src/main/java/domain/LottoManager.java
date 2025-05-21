package domain;

import domain.generator.LottoGenerator;

import java.util.*;


public class LottoManager {
    //전체적인 로또를 구매하는 과정을 관리
    private static final int PRICE_PER_LOTTO = 1000;
    private final LottoGenerator generator;

    public LottoManager(LottoGenerator generator) {
        this.generator = generator;
    }

    public Lottos purchaseLottos(int money) {
        validateMoney(money);
        int lottoCount = money / PRICE_PER_LOTTO;
        List<Lotto> lottos = generateLottos(lottoCount);
        return new Lottos(lottos);
    }

    private void validateMoney(int money) {
        if (money < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException("구입 금액의 최소단위는 1000원입니다.");
        }
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generator.generate());
        }
        return lottos;
    }
}

