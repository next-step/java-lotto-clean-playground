package domain;

import java.util.*;


public class LottoManager {
    //전체적인 로또를 구매하는 과정을 관리
    private static final int PRICE_PER_LOTTO = 1000;

    public LottoHistory purchaseLottos(int money) {
        validateMoney(money);
        int lottoCount = money / PRICE_PER_LOTTO;
        List<Lotto> lottos = generateLottos(lottoCount);
        return new LottoHistory(lottos);
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("구입 금액은 0보다 커야 합니다.");
        }
        if (money < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException("구입 금액의 최소단위는 1000원입니다.");
        }
    }

    private List<Lotto> generateLottos(int lottoCount) {
        LottoGenerator generator = new AutoLottoGenerator();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generator.generate());
        }
        return lottos;
    }
}

